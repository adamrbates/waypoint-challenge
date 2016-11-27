import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestProcessor {

    @Test
    public void testDefault() throws TimeTravelException {
        Processor p = new Processor();
        assertEquals(p.getDuration(), 0, 1e-10);
        assertEquals(p.getDistance(), 0, 1e-10);
        assertEquals(p.getDurationSpeeding(), 0, 1e-10);
        assertEquals(p.getDistanceSpeeding(), 0, 1e-10);
    }

    @Test
    public void testOne() throws TimeTravelException {
        Processor p = new Processor();
        p.push(new Waypoint(0,0,10,50,0));
        assertEquals(p.getDuration(), 0, 1e-10);
        assertEquals(p.getDistance(), 0, 1e-10);
        assertEquals(p.getDurationSpeeding(), 0, 1e-10);
        assertEquals(p.getDistanceSpeeding(), 0, 1e-10);
    }

    @Test
    public void testOneSpeeding() throws TimeTravelException {
        Processor p = new Processor();
        p.push(new Waypoint(0,0,10,5,0));
        assertEquals(p.getDuration(), 0, 1e-10);
        assertEquals(p.getDistance(), 0, 1e-10);
        assertEquals(p.getDurationSpeeding(), 0, 1e-10);
        assertEquals(p.getDistanceSpeeding(), 0, 1e-10);
    }

    @Test
    public void testTwo() throws TimeTravelException {
        Processor p = new Processor();
        p.push(new Waypoint(0,0,10,50,0));
        p.push(new Waypoint(0,0,10,50,1));
        assertEquals(p.getDuration(), 1, 1e-10);
        assertEquals(p.getDistance(), 0, 1e-10);
        assertEquals(p.getDurationSpeeding(), 0, 1e-10);
        assertEquals(p.getDistanceSpeeding(), 0, 1e-10);
    }

    @Test
    public void testTwoSpeeding() throws TimeTravelException {
        Processor p = new Processor();
        p.push(new Waypoint(0,0,10,5,0));
        p.push(new Waypoint(0,0,10,5,1));
        assertEquals(p.getDuration(), 1, 1e-10);
        assertEquals(p.getDistance(), 0, 1e-10);
        assertEquals(p.getDurationSpeeding(), 1, 1e-10);
        assertEquals(p.getDistanceSpeeding(), 0, 1e-10);
    }

    @Test
    public void testTwoNotSpeedingEqual() throws TimeTravelException {
        Processor p = new Processor();
        p.push(new Waypoint(0,0,5,5,0));
        p.push(new Waypoint(0,0,5,5,1));
        assertEquals(p.getDuration(), 1, 1e-10);
        assertEquals(p.getDistance(), 0, 1e-10);
        assertEquals(p.getDurationSpeeding(), 0, 1e-10);
        assertEquals(p.getDistanceSpeeding(), 0, 1e-10);
    }

    @Test
    public void testTwoKnownDistance() throws TimeTravelException {
        Processor p = new Processor();
        p.push(new Waypoint(0,0,10,50,0));
        p.push(new Waypoint(0,0.01,10,50,1));
        assertEquals(p.getDuration(), 1, 1e-10);
        assertEquals(p.getDistance(), 1111, 1);
        assertEquals(p.getDurationSpeeding(), 0, 1e-10);
        assertEquals(p.getDistanceSpeeding(), 0, 1e-10);
    }

    @Test
    public void testTwoKnownDistanceSpeeding() throws TimeTravelException {
        Processor p = new Processor();
        p.push(new Waypoint(0,0,10,5,0));
        p.push(new Waypoint(0,0.01,10,5,1));
        assertEquals(p.getDuration(), 1, 1e-10);
        assertEquals(p.getDistance(), 1111, 1);
        assertEquals(p.getDurationSpeeding(), 1, 1e-10);
        assertEquals(p.getDistanceSpeeding(), 1111, 1);
    }

    @Test(expected=TimeTravelException.class)
    public void testOutOfOrderTimestamp() throws TimeTravelException {
        Processor p = new Processor();
        p.push(new Waypoint(0,0,10,50,1));
        p.push(new Waypoint(0,0,10,50,0));
    }

    @Test
    public void testClonedTimestamp() throws TimeTravelException {
        Processor p = new Processor();
        p.push(new Waypoint(0,0,10,50,0));
        p.push(new Waypoint(0,0,10,50,0));
        assertEquals(p.getDuration(), 0, 1e-10);
        assertEquals(p.getDistance(), 0, 1e-10);
        assertEquals(p.getDurationSpeeding(), 0, 1e-10);
        assertEquals(p.getDistanceSpeeding(), 0, 1e-10);
    }

    @Test
    public void testTen() throws TimeTravelException {
        Processor p = new Processor();
        p.push(new Waypoint(164.618529438, -24.7095502, 10, 50, 0));
        p.push(new Waypoint(151.110694749, -23.2204139, 10, 50, 1));
        p.push(new Waypoint(12.7936894223, -80.6918236, 10, 50, 2));
        p.push(new Waypoint(54.2415798948, -47.8955804, 10, 50, 3));
        p.push(new Waypoint(36.0436689059, -3.86980430, 10, 50, 4));
        p.push(new Waypoint(145.090578886, 51.36259566, 10, 50, 5));
        p.push(new Waypoint(-40.132703674, -82.7784532, 10, 50, 6));
        p.push(new Waypoint(-17.909295020, 50.63503063, 10, 50, 7));
        p.push(new Waypoint(-66.019606996, 75.41725851, 10, 50, 8));
        p.push(new Waypoint(14.2869912285, -47.9152051, 10, 50, 9));
        assertEquals(p.getDuration(), 9, 1e-10);
        assertEquals(p.getDistance(), 8.013E7, 1e4);
        assertEquals(p.getDurationSpeeding(), 0, 1e-10);
        assertEquals(p.getDistanceSpeeding(), 0, 1e-10);
    }

    @Test
    public void testTenSpeeding() throws TimeTravelException {
        Processor p = new Processor();
        p.push(new Waypoint(164.618529438, -24.7095502, 10, 50, 0));
        p.push(new Waypoint(151.110694749, -23.2204139, 10, 5, 1));
        p.push(new Waypoint(12.7936894223, -80.6918236, 10, 50, 2));
        p.push(new Waypoint(54.2415798948, -47.8955804, 10, 5, 3));
        p.push(new Waypoint(36.0436689059, -3.86980430, 10, 50, 4));
        p.push(new Waypoint(145.090578886, 51.36259566, 10, 5, 5));
        p.push(new Waypoint(-40.132703674, -82.7784532, 10, 50, 6));
        p.push(new Waypoint(-17.909295020, 50.63503063, 10, 5, 7));
        p.push(new Waypoint(-66.019606996, 75.41725851, 10, 50, 8));
        p.push(new Waypoint(14.2869912285, -47.9152051, 10, 5, 9));
        assertEquals(p.getDuration(), 9, 1e-10);
        assertEquals(p.getDistance(), 8.013E7, 1e4);
        assertEquals(p.getDurationSpeeding(), 5, 1e-10);
        assertEquals(p.getDistanceSpeeding(), 4.6738E7, 1e4);
    }
}

