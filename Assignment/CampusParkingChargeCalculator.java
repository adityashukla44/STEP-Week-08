import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CampusParkingChargeCalculator {
    abstract static class Vehicle {
        final int hours;
        Vehicle(int hours) { this.hours = hours; }
        abstract double charge();
        abstract String type();
    }
    static class Bike extends Vehicle {
        Bike(int hours) { super(hours); }
        @Override double charge() { return 10.0 * hours; }
        @Override String type() { return "BIKE"; }
    }
    static class Car extends Vehicle {
        Car(int hours) { super(hours); }
        @Override double charge() { return 30.0 + 20.0 * (hours - 1); }
        @Override String type() { return "CAR"; }
    }
    static class Truck extends Vehicle {
        Truck(int hours) { super(hours); }
        @Override double charge() { return Math.max(100.0, 50.0 * hours); }
        @Override String type() { return "TRUCK"; }
    }
    private static Vehicle createVehicle(String type, int hours) {
        return switch (type.toUpperCase(Locale.ROOT)) {
            case "BIKE" -> new Bike(hours);
            case "CAR" -> new Car(hours);
            case "TRUCK" -> new Truck(hours);
            default -> throw new IllegalArgumentException("Unsupported vehicle type: " + type);
        };
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            System.out.println("Enter N followed by vehicle type and parked hours for each vehicle.");
            return;
        }
        int n = scanner.nextInt();
        List<Vehicle> vehicles = new ArrayList<>();
        for (int i = 0; i < n; i++) vehicles.add(createVehicle(scanner.next(), scanner.nextInt()));
        double total = 0.0;
        for (Vehicle vehicle : vehicles) {
            double charge = vehicle.charge();
            System.out.printf(Locale.US, "%s: %.2f%n", vehicle.type(), charge);
            total += charge;
        }
        System.out.printf(Locale.US, "Total: %.2f%n", total);
    }
}
