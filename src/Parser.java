import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.io.FileReader;
import java.util.Iterator;
import java.time.Instant;
import java.time.format.DateTimeParseException;

public final class Parser {
    private final JSONParser mParser;
    private final JSONArray mWaypointArray;
    private Iterator<JSONObject> mIterator;
    
    /* create a Parser with a string. */
    public Parser(final String str) throws ParseException {
        mParser = new JSONParser();
        try {
            mWaypointArray = (JSONArray) mParser.parse(str);
        } catch (Exception e) {
            throw new ParseException("Error parsing given input.", e);
        }
        mIterator = mWaypointArray.iterator();
        if (mIterator == null) {
            throw new ParseException("Error parsing given input.");
        }
    }

    public Parser(final FileReader file) throws ParseException {
        mParser = new JSONParser();
        try {
            mWaypointArray = (JSONArray) mParser.parse(file);
        } catch (Exception e) {
            throw new ParseException("Error parsing given input.", e);
        }
        mIterator = mWaypointArray.iterator();
        if (mIterator == null) {
            throw new ParseException("Error parsing given input.");
        }
    }

    public Waypoint next() throws ParseException {
        if (!mIterator.hasNext()) {
            return null;
        }
        
        final JSONObject waypoint = mIterator.next();
        if (waypoint == null) {
            throw new ParseException("Error parsing given input.");
        }

        // timestamp
        String ts = null;
        try {
            ts = (String) waypoint.get("timestamp");
        } catch (java.lang.ClassCastException e) {
            throw new ParseException("invalid timestamp.");
        }
        if (ts == null) {
            throw new ParseException("Missing timestamp.");
        }
        long timestamp = 0;
        try {
            timestamp = Instant.parse(ts).getEpochSecond();
        } catch (DateTimeParseException e) {
            throw new ParseException("Could not parse timestamp.");
        }

        // position
        JSONObject position = null;
        try {
            position = (JSONObject) waypoint.get("position");
        } catch (java.lang.ClassCastException e) {
            throw new ParseException("Invalid position.");
        }
        if (position == null) {
            throw new ParseException("Missing position.");
        }

        // latitude
        Object next = position.get("latitude");
        if (next == null) {
            throw new ParseException("Missing latitude.");
        }
        double latitude = 0;
        try {
            latitude = (double) next;
        } catch (java.lang.ClassCastException e) {
            throw new ParseException("Invalid latitude.");
        }
        if (latitude < -90 || latitude > 90) {
            throw new ParseException("Invalid latitude.");
        }

        next = position.get("longitude");
        if (next == null) {
            throw new ParseException("Missing longitude.");
        }
        double longitude = 0;
        try {
            longitude = (double) next;
        } catch (java.lang.ClassCastException e) {
            throw new ParseException("Invalid longitude.");
        }
        if (longitude < -180 || longitude > 180) {
            throw new ParseException("Invalid longitude.");
        }

        next = waypoint.get("speed");
        if (next == null) {
            throw new ParseException("Missing speed.");
        }
        double speed = 0;
        try {
            speed = (double) next;
        } catch (java.lang.ClassCastException e) {
            throw new ParseException("Invalid speed.");
        }
        if (speed < 0) {
            throw new ParseException("Invalid speed.");
        }
        
        next = waypoint.get("speed_limit");
        if (next == null) {
            throw new ParseException("Missing speed_limit.");
        }
        double speedlimit = 0;
        try {
            speedlimit = (double) next;
        } catch (java.lang.ClassCastException e) {
            throw new ParseException("Invalid speed_limit.");
        }
        if (speedlimit < 0) {
            throw new ParseException("Invalid speedlimit.");
        }
        
        final Waypoint w = new Waypoint(
               longitude, latitude, speed, speedlimit, timestamp);

        return w;
    }

    public boolean hasNext() {
        return mIterator.hasNext();
    }

    public void reset() {
        mIterator = mWaypointArray.iterator();
    }
}
