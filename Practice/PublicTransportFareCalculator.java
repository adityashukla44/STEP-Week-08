import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class PublicTransportFareCalculator {
    abstract static class Transport {
        final double distance;
        Transport(double distance) { this.distance = distance; }
        abstract double fare();
        abstract String type();
    }
    static class Bus extends Transport {
        Bus(double distance) { super(distance); }
        @Override double fare() { return Math.min(10.0, 2.0 + 0.10 * distance); }
        @Override String type() { return "BUS"; }
    }
    static class Train extends Transport {
        Train(double distance) { super(distance); }
        @Override double fare() { return 3.0 + 0.15 * distance; }
        @Override String type() { return "TRAIN"; }
    }
    static class Metro extends Transport {
        final double peakHourFactor;
        Metro(double distance, double factor) { super(distance); this.peakHourFactor = factor; }
        @Override double fare() { return (1.50 + 0.20 * distance) * peakHourFactor; }
        @Override String type() { return "METRO"; }
    }

    private static Transport createTransport(String type, double distance, double factor) {
        return switch (type.toUpperCase(Locale.ROOT)) {
            case "BUS" -> new Bus(distance);
            case "TRAIN" -> new Train(distance);
            case "METRO" -> new Metro(distance, factor);
            default -> throw new IllegalArgumentException("Unsupported transport type: " + type);
        };
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            System.out.println("Enter N, followed by transport type, distance, and metro peak factor when applicable.");
            return;
        }
        int n = scanner.nextInt();
        List<Transport> journeys = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            double distance = scanner.nextDouble();
            double factor = type.equalsIgnoreCase("METRO") ? scanner.nextDouble() : 1.0;
            journeys.add(createTransport(type, distance, factor));
        }
        double total = 0.0;
        for (Transport journey : journeys) {
            double fare = journey.fare();
            System.out.printf(Locale.US, "%s: %.2f%n", journey.type(), fare);
            total += fare;
        }
        System.out.printf(Locale.US, "Total: %.2f%n", total);
    }
}
