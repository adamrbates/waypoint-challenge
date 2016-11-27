import java.io.FileReader;
import java.lang.StringBuilder;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            StringBuilder sb = new StringBuilder();
            sb.append("Ussage: java Main <waypoints.json>\n");
            sb.append("    Expected a waypoints json file.");
            System.err.println(sb.toString());
            System.exit(1);
        }
        try {
            Parser parser = new Parser(new FileReader(args[0]));
            Processor processor = new Processor();
            while (parser.hasNext()) {
                Waypoint waypoint = parser.next();
                processor.push(waypoint);
            }

            StringBuilder sb = new StringBuilder();
            sb.append("Duration: ");
            sb.append(processor.getDuration());
            sb.append("\nDistance: ");
            sb.append(processor.getDistance());
            sb.append("\nDuration Speeding: ");
            sb.append(processor.getDurationSpeeding());
            sb.append("\nDistance Speeding: ");
            sb.append(processor.getDistanceSpeeding());
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
