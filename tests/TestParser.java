import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestParser {

    @Test
    public void testOne() throws ParseException {
        String one = new StringBuilder()
            .append("[\n")
            .append("{\n")
            .append("\"timestamp\": \"2016-06-21T12:00:00.000Z\",\n")
            .append("\"position\": {\n")
            .append("\"latitude\": 59.334,\n")
            .append("\"longitude\": 18.0667\n") 
            .append("},\n")
            .append("\"speed\": 6.3889,\n")
            .append("\"speed_limit\": 8.33\n")
            .append("}\n")
            .append("]\n")
            .toString();

        Parser parser = new Parser(one);
        assertEquals(parser.hasNext(), true);
        Waypoint waypoint = parser.next();
        assertEquals(parser.hasNext(), false);
        assertEquals(waypoint.getTimestamp(), 1466510400);
        assertEquals(waypoint.getLatitude(), 59.334, 1e-10);
        assertEquals(waypoint.getLongitude(), 18.0667, 1e-10);
        assertEquals(waypoint.getSpeed(), 6.3889, 1e-10);
        assertEquals(waypoint.getSpeedLimit(), 8.33, 1e-10);
    }

    @Test
    public void testEmpty() throws ParseException {
        String one = new StringBuilder()
            .append("[\n")
            .append("]\n")
            .toString();

        Parser parser = new Parser(one);
        assertEquals(parser.hasNext(), false);
    }

    @Test(expected=ParseException.class)
    public void testBroken() throws ParseException {
        String one = new StringBuilder()
            .append("[\n")
            .append("\n")
            .toString();

        Parser parser = new Parser(one);
        fail();
    }

    @Test(expected=ParseException.class)
    public void testMissingTimestamp() throws ParseException {
        String one = new StringBuilder()
            .append("[\n")
            .append("{\n")
            //.append("\"timestamp\": \"2016-06-21T12:00:00.000Z\",\n")
            .append("\"position\": {\n")
            .append("\"latitude\": 59.334,\n")
            .append("\"longitude\": 18.0667\n") 
            .append("},\n")
            .append("\"speed\": 6.3889,\n")
            .append("\"speed_limit\": 8.33\n")
            .append("}\n")
            .append("]\n")
            .toString();

        Parser parser = new Parser(one);
        assertEquals(parser.hasNext(), true);
        Waypoint waypoint = parser.next();
        fail();
    }

    @Test(expected=ParseException.class)
    public void testMissingPosition() throws ParseException {
        String one = new StringBuilder()
            .append("[\n")
            .append("{\n")
            .append("\"timestamp\": \"2016-06-21T12:00:00.000Z\",\n")
            //.append("\"position\": {\n")
            //.append("\"latitude\": 59.334,\n")
            //.append("\"longitude\": 18.0667\n") 
            //.append("},\n")
            .append("\"speed\": 6.3889,\n")
            .append("\"speed_limit\": 8.33\n")
            .append("}\n")
            .append("]\n")
            .toString();

        Parser parser = new Parser(one);
        assertEquals(parser.hasNext(), true);
        Waypoint waypoint = parser.next();
        fail();
    }

    @Test(expected=ParseException.class)
    public void testMissingLongitude() throws ParseException {
        String one = new StringBuilder()
            .append("[\n")
            .append("{\n")
            .append("\"timestamp\": \"2016-06-21T12:00:00.000Z\",\n")
            .append("\"position\": {\n")
            .append("\"latitude\": 59.334\n")
            //.append("\"longitude\": 18.0667\n") 
            .append("},\n")
            .append("\"speed\": 6.3889,\n")
            .append("\"speed_limit\": 8.33\n")
            .append("}\n")
            .append("]\n")
            .toString();

        Parser parser = new Parser(one);
        assertEquals(parser.hasNext(), true);
        Waypoint waypoint = parser.next();
        fail();
    }

    @Test(expected=ParseException.class)
    public void testMissingLatitude() throws ParseException {
        String one = new StringBuilder()
            .append("[\n")
            .append("{\n")
            .append("\"timestamp\": \"2016-06-21T12:00:00.000Z\",\n")
            .append("\"position\": {\n")
            //.append("\"latitude\": 59.334,\n")
            .append("\"longitude\": 18.0667\n") 
            .append("},\n")
            .append("\"speed\": 6.3889,\n")
            .append("\"speed_limit\": 8.33\n")
            .append("}\n")
            .append("]\n")
            .toString();

        Parser parser = new Parser(one);
        assertEquals(parser.hasNext(), true);
        Waypoint waypoint = parser.next();
        fail();
    }

    @Test(expected=ParseException.class)
    public void testMissingSpeed() throws ParseException {
        String one = new StringBuilder()
            .append("[\n")
            .append("{\n")
            .append("\"timestamp\": \"2016-06-21T12:00:00.000Z\",\n")
            .append("\"position\": {\n")
            .append("\"latitude\": 59.334,\n")
            .append("\"longitude\": 18.0667\n") 
            .append("},\n")
            //.append("\"speed\": 6.3889,\n")
            .append("\"speed_limit\": 8.33\n")
            .append("}\n")
            .append("]\n")
            .toString();

        Parser parser = new Parser(one);
        assertEquals(parser.hasNext(), true);
        Waypoint waypoint = parser.next();
        fail();
    }

    @Test(expected=ParseException.class)
    public void testMissingSpeedlimit() throws ParseException {
        String one = new StringBuilder()
            .append("[\n")
            .append("{\n")
            .append("\"timestamp\": \"2016-06-21T12:00:00.000Z\",\n")
            .append("\"position\": {\n")
            .append("\"latitude\": 59.334,\n")
            .append("\"longitude\": 18.0667\n") 
            .append("},\n")
            .append("\"speed\": 6.3889\n")
            //.append("\"speed_limit\": 8.33\n")
            .append("}\n")
            .append("]\n")
            .toString();

        Parser parser = new Parser(one);
        assertEquals(parser.hasNext(), true);
        Waypoint waypoint = parser.next();
        fail();
    }

    @Test(expected=ParseException.class)
    public void testInvalidTimestamp() throws ParseException {
        String one = new StringBuilder()
            .append("[\n")
            .append("{\n")
            .append("\"timestamp\": \"asdasd2016-06-21T12:00:00.000Z\",\n")
            .append("\"position\": {\n")
            .append("\"latitude\": 59.334,\n")
            .append("\"longitude\": 18.0667\n") 
            .append("},\n")
            .append("\"speed\": 6.3889,\n")
            .append("\"speed_limit\": 8.33\n")
            .append("}\n")
            .append("]\n")
            .toString();

        Parser parser = new Parser(one);
        assertEquals(parser.hasNext(), true);
        Waypoint waypoint = parser.next();
        fail();
    }

    @Test(expected=ParseException.class)
    public void testInvalidLatitude() throws ParseException {
        String one = new StringBuilder()
            .append("[\n")
            .append("{\n")
            .append("\"timestamp\": \"2016-06-21T12:00:00.000Z\",\n")
            .append("\"position\": {\n")
            .append("\"latitude\": 59,\n")
            .append("\"longitude\": 18.0667\n") 
            .append("},\n")
            .append("\"speed\": 6.3889,\n")
            .append("\"speed_limit\": 8.33\n")
            .append("}\n")
            .append("]\n")
            .toString();

        Parser parser = new Parser(one);
        assertEquals(parser.hasNext(), true);
        Waypoint waypoint = parser.next();
        fail();
    }

    @Test(expected=ParseException.class)
    public void testInvalidLongitude() throws ParseException {
        String one = new StringBuilder()
            .append("[\n")
            .append("{\n")
            .append("\"timestamp\": \"2016-06-21T12:00:00.000Z\",\n")
            .append("\"position\": {\n")
            .append("\"latitude\": 59.0,\n")
            .append("\"longitude\": 18\n") 
            .append("},\n")
            .append("\"speed\": 6.3889,\n")
            .append("\"speed_limit\": 8.33\n")
            .append("}\n")
            .append("]\n")
            .toString();

        Parser parser = new Parser(one);
        assertEquals(parser.hasNext(), true);
        Waypoint waypoint = parser.next();
        fail();
    }

    @Test(expected=ParseException.class)
    public void testInvalidSpeed() throws ParseException {
        String one = new StringBuilder()
            .append("[\n")
            .append("{\n")
            .append("\"timestamp\": \"2016-06-21T12:00:00.000Z\",\n")
            .append("\"position\": {\n")
            .append("\"latitude\": 59.0,\n")
            .append("\"longitude\": 18.0667\n") 
            .append("},\n")
            .append("\"speed\": 6,\n")
            .append("\"speed_limit\": 8.33\n")
            .append("}\n")
            .append("]\n")
            .toString();

        Parser parser = new Parser(one);
        assertEquals(parser.hasNext(), true);
        Waypoint waypoint = parser.next();
        fail();
    }

    @Test(expected=ParseException.class)
    public void testInvalidSpeedlimit() throws ParseException {
        String one = new StringBuilder()
            .append("[\n")
            .append("{\n")
            .append("\"timestamp\": \"2016-06-21T12:00:00.000Z\",\n")
            .append("\"position\": {\n")
            .append("\"latitude\": 59.0,\n")
            .append("\"longitude\": 18.0667\n") 
            .append("},\n")
            .append("\"speed\": 6.3889,\n")
            .append("\"speed_limit\": 8\n")
            .append("}\n")
            .append("]\n")
            .toString();

        Parser parser = new Parser(one);
        assertEquals(parser.hasNext(), true);
        Waypoint waypoint = parser.next();
        fail();
    }

    @Test(expected=ParseException.class)
    public void testOutOfRangeLatitude() throws ParseException {
        String one = new StringBuilder()
            .append("[\n")
            .append("{\n")
            .append("\"timestamp\": \"2016-06-21T12:00:00.000Z\",\n")
            .append("\"position\": {\n")
            .append("\"latitude\": 91.0,\n")
            .append("\"longitude\": 18.0667\n") 
            .append("},\n")
            .append("\"speed\": 6.3889,\n")
            .append("\"speed_limit\": 8.33\n")
            .append("}\n")
            .append("]\n")
            .toString();

        Parser parser = new Parser(one);
        assertEquals(parser.hasNext(), true);
        Waypoint waypoint = parser.next();
        fail();
    }

    @Test(expected=ParseException.class)
    public void testOutOfRangeLongitude() throws ParseException {
        String one = new StringBuilder()
            .append("[\n")
            .append("{\n")
            .append("\"timestamp\": \"2016-06-21T12:00:00.000Z\",\n")
            .append("\"position\": {\n")
            .append("\"latitude\": 59.0,\n")
            .append("\"longitude\": -200.0\n") 
            .append("},\n")
            .append("\"speed\": 6.3889,\n")
            .append("\"speed_limit\": 8.33\n")
            .append("}\n")
            .append("]\n")
            .toString();

        Parser parser = new Parser(one);
        assertEquals(parser.hasNext(), true);
        Waypoint waypoint = parser.next();
        fail();
    }

    @Test(expected=ParseException.class)
    public void testOutOfRangeSpeed() throws ParseException {
        String one = new StringBuilder()
            .append("[\n")
            .append("{\n")
            .append("\"timestamp\": \"2016-06-21T12:00:00.000Z\",\n")
            .append("\"position\": {\n")
            .append("\"latitude\": 59.0,\n")
            .append("\"longitude\": -20.0\n") 
            .append("},\n")
            .append("\"speed\": -6.3889,\n")
            .append("\"speed_limit\": 8.33\n")
            .append("}\n")
            .append("]\n")
            .toString();

        Parser parser = new Parser(one);
        assertEquals(parser.hasNext(), true);
        Waypoint waypoint = parser.next();
        fail();
    }

    @Test(expected=ParseException.class)
    public void testOutOfRangeSpeedLimit() throws ParseException {
        String one = new StringBuilder()
            .append("[\n")
            .append("{\n")
            .append("\"timestamp\": \"2016-06-21T12:00:00.000Z\",\n")
            .append("\"position\": {\n")
            .append("\"latitude\": 59.0,\n")
            .append("\"longitude\": -20.0\n") 
            .append("},\n")
            .append("\"speed\": 6.3889,\n")
            .append("\"speed_limit\": -8.33\n")
            .append("}\n")
            .append("]\n")
            .toString();

        Parser parser = new Parser(one);
        assertEquals(parser.hasNext(), true);
        Waypoint waypoint = parser.next();
        fail();
    }

    @Test
    public void testTwo() throws ParseException {
        String one = new StringBuilder()
            .append("[\n")
            .append("{\n")
            .append("\"timestamp\": \"2016-06-21T12:00:00.000Z\",\n")
            .append("\"position\": {\n")
            .append("\"latitude\": 59.0,\n")
            .append("\"longitude\": -20.0\n") 
            .append("},\n")
            .append("\"speed\": 6.3889,\n")
            .append("\"speed_limit\": 8.33\n")
            .append("},\n")
            .append("{\n")
            .append("\"timestamp\": \"2016-06-21T12:00:00.000Z\",\n")
            .append("\"position\": {\n")
            .append("\"latitude\": 59.0,\n")
            .append("\"longitude\": -20.0\n") 
            .append("},\n")
            .append("\"speed\": 6.3889,\n")
            .append("\"speed_limit\": 8.33\n")
            .append("}\n")
            .append("]\n")
            .toString();

        Parser parser = new Parser(one);
        assertEquals(parser.hasNext(), true);
        Waypoint waypoint = parser.next();
        assertEquals(parser.hasNext(), true);
        waypoint = parser.next();
        assertEquals(parser.hasNext(), false);
    }

    @Test
    public void testOnehundred() throws ParseException {
        StringBuilder one = new StringBuilder();
        one.append("[\n");
        for (int i = 0; i < 99; i++) {
            one.append("{\n");
            one.append("\"timestamp\": \"2016-06-21T12:00:00.000Z\",\n");
            one.append("\"position\": {\n");
            one.append("\"latitude\": 59.0,\n");
            one.append("\"longitude\": -20.0\n") ;
            one.append("},\n");
            one.append("\"speed\": 6.3889,\n");
            one.append("\"speed_limit\": 8.33\n");
            one.append("},\n");
        }
        one.append("{\n");
        one.append("\"timestamp\": \"2016-06-21T12:00:00.000Z\",\n");
        one.append("\"position\": {\n");
        one.append("\"latitude\": 59.0,\n");
        one.append("\"longitude\": -20.0\n");
        one.append("},\n");
        one.append("\"speed\": 6.3889,\n");
        one.append("\"speed_limit\": 8.33\n");
        one.append("}\n");
        one.append("]\n");

        Parser parser = new Parser(one.toString());

        Waypoint waypoint = null;
        for (int i = 0; i < 100; i++) {
            assertEquals(parser.hasNext(), true);
            waypoint = parser.next();
        }
        assertEquals(parser.hasNext(), false);
    }

    @Test
    public void testReset() throws ParseException {
        String one = new StringBuilder()
            .append("[\n")
            .append("{\n")
            .append("\"timestamp\": \"2016-06-21T12:00:00.000Z\",\n")
            .append("\"position\": {\n")
            .append("\"latitude\": 59.0,\n")
            .append("\"longitude\": -20.0\n") 
            .append("},\n")
            .append("\"speed\": 6.3889,\n")
            .append("\"speed_limit\": 8.33\n")
            .append("}\n")
            .append("]\n")
            .toString();

        Parser parser = new Parser(one);
        assertEquals(parser.hasNext(), true);
        Waypoint waypoint = parser.next();
        assertEquals(parser.hasNext(), false);
        parser.reset();
        assertEquals(parser.hasNext(), true);
    }

    @Test
    public void testNextTwice() throws ParseException {
        String one = new StringBuilder()
            .append("[\n")
            .append("{\n")
            .append("\"timestamp\": \"2016-06-21T12:00:00.000Z\",\n")
            .append("\"position\": {\n")
            .append("\"latitude\": 59.0,\n")
            .append("\"longitude\": -20.0\n") 
            .append("},\n")
            .append("\"speed\": 6.3889,\n")
            .append("\"speed_limit\": 8.33\n")
            .append("}\n")
            .append("]\n")
            .toString();

        Parser parser = new Parser(one);
        assertEquals(parser.hasNext(), true);
        Waypoint waypoint = parser.next();
        assertEquals(parser.hasNext(), false);
        waypoint = parser.next();
        assertNull(waypoint);
        assertEquals(parser.hasNext(), false);
    }
}
