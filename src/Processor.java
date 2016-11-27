import static com.javadocmd.simplelatlng.LatLngTool.distance;
import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.util.LengthUnit;

public final class Processor {
    private double mLastLongitude;
    private double mLastLatitude;
    private double mLastSpeed;
    private double mLastSpeedLimit;
    private long mLastTime;
    private int mCount;
    private long mDuration;
    private long mDurationSpeeding;
    private double mDistance;
    private double mDistanceSpeeding;

    public void push(final Waypoint waypoint) throws TimeTravelException {
        if (mCount > 0) {
            final long delta = waypoint.getTimestamp() - mLastTime;
            if (delta < 0) {
                throw new TimeTravelException("Out of order timestamp.");
            }
            mDuration += delta;

            final double dist = calcDistance(
                    waypoint.getLongitude(), waypoint.getLatitude());
            mDistance += dist;
            
            if (waypoint.getSpeed() > waypoint.getSpeedLimit()) {
                mDurationSpeeding += delta;
                mDistanceSpeeding += dist;
            }
        }

        mLastLongitude = waypoint.getLongitude();
        mLastLatitude = waypoint.getLatitude();
        mLastSpeed = waypoint.getSpeed();
        mLastSpeedLimit = waypoint.getSpeedLimit();
        mLastTime = waypoint.getTimestamp();
        mCount++;
    }

    public double getDistance() {
        return mDistance;
    }

    public double getDistanceSpeeding() {
        return mDistanceSpeeding;
    }

    public long getDuration() {
        return mDuration;
    }

    public long getDurationSpeeding() {
        return mDurationSpeeding;
    }

    private double calcDistance(double longitude, double latitude) {
        LatLng a = new LatLng(latitude, longitude);
        LatLng b = new LatLng(mLastLatitude, mLastLongitude);
        return distance(a, b, LengthUnit.KILOMETER) * 1000.0; // meters
    }
}
