public final class Waypoint {
    private final double mLongitude;
    private final double mLatitude;
    private final double mSpeed;
    private final double mSpeedLimit;
    private final long mTimestamp;

    public Waypoint(
            final double longitude,
            final double latitude,
            final double speed,
            final double speedLimit,
            final long timestamp)
    {
        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Invalid longitude.");
        }
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Invalid latitude.");
        }
        if (speed < 0) {
            throw new IllegalArgumentException("Invalid speed.");
        }
        if (speedLimit < 0) {
            throw new IllegalArgumentException("Invalid speedLimit.");
        }
        mLongitude = longitude;
        mLatitude = latitude;
        mSpeed = speed;
        mSpeedLimit = speedLimit;
        mTimestamp = timestamp;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public double getSpeed() {
        return mSpeed;
    }

    public double getSpeedLimit() {
        return mSpeedLimit;
    }

    public long getTimestamp() {
        return mTimestamp;
    }
}
