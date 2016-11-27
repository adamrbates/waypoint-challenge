import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestWaypoint {

    @Test
    public void testOne() {
        Waypoint waypoint = new Waypoint(0,0,0,0,0);
        assertEquals(waypoint.getLongitude(), 0, 1e-10);
        assertEquals(waypoint.getLatitude(), 0, 1e-10);
        assertEquals(waypoint.getSpeed(), 0, 1e-10);
        assertEquals(waypoint.getSpeedLimit(), 0, 1e-10);
        assertEquals(waypoint.getTimestamp(), 0, 1e-10);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testPositiveLongitude() {
        Waypoint waypoint = new Waypoint(200,0,0,0,0);
        fail();
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNegativeLongitude() {
        Waypoint waypoint = new Waypoint(-200,0,0,0,0);
        fail();
    }

    @Test(expected=IllegalArgumentException.class)
    public void testPositiveLatitude() {
        Waypoint waypoint = new Waypoint(0,100,0,0,0);
        fail();
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNegativeLatidude() {
        Waypoint waypoint = new Waypoint(0,-100,0,0,0);
        fail();
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNegativeSpeed() {
        Waypoint waypoint = new Waypoint(0,0,-1,0,0);
        fail();
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNegativeSpeedLimit() {
        Waypoint waypoint = new Waypoint(0,0,0,-1,0);
        fail();
    }
}
