import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class DeliveryFeeCalculator {
    abstract static class Delivery {
        final double weight;
        final double distance;
        Delivery(double weight, double distance) { this.weight = weight; this.distance = distance; }
        abstract double calculateFee();
        abstract String type();
    }
    static class StandardDelivery extends Delivery {
        StandardDelivery(double w, double d) { super(w, d); }
        @Override double calculateFee() { return 5.0 + 0.50 * weight + 0.10 * distance; }
        @Override String type() { return "STANDARD"; }
    }
    static class ExpressDelivery extends Delivery {
        ExpressDelivery(double w, double d) { super(w, d); }
        @Override double calculateFee() { return 15.0 + 1.00 * weight + 0.20 * distance; }
        @Override String type() { return "EXPRESS"; }
    }
    static class InternationalDelivery extends Delivery {
        final double customsFee;
        InternationalDelivery(double w, double d, double customsFee) {
            super(w, d); this.customsFee = customsFee;
        }
        @Override double calculateFee() { return 25.0 + 2.00 * weight + 0.50 * distance + customsFee; }
        @Override String type() { return "INTERNATIONAL"; }
    }

    private static Delivery createDelivery(String type, double weight, double distance, double customsFee) {
        return switch (type.toUpperCase(Locale.ROOT)) {
            case "STANDARD" -> new StandardDelivery(weight, distance);
            case "EXPRESS" -> new ExpressDelivery(weight, distance);
            case "INTERNATIONAL" -> new InternationalDelivery(weight, distance, customsFee);
            default -> throw new IllegalArgumentException("Unsupported delivery type: " + type);
        };
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            System.out.println("Enter N, followed by delivery type, weight, distance, and optional customs fee.");
            return;
        }
        int n = scanner.nextInt();
        List<Delivery> deliveries = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            double weight = scanner.nextDouble();
            double distance = scanner.nextDouble();
            double customs = type.equalsIgnoreCase("INTERNATIONAL") ? scanner.nextDouble() : 0.0;
            deliveries.add(createDelivery(type, weight, distance, customs));
        }
        double total = 0.0;
        for (Delivery delivery : deliveries) {
            double fee = delivery.calculateFee();
            System.out.printf(Locale.US, "%s: %.2f%n", delivery.type(), fee);
            total += fee;
        }
        System.out.printf(Locale.US, "Total: %.2f%n", total);
    }
}
