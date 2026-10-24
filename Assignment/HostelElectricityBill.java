import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class HostelElectricityBill {
    abstract static class Room {
        final int units;
        Room(int units) { this.units = units; }
        abstract double bill();
        abstract String type();
    }
    static class SingleRoom extends Room {
        SingleRoom(int units) { super(units); }
        @Override double bill() { return units * 8.0; }
        @Override String type() { return "SINGLE"; }
    }
    static class SharedRoom extends Room {
        final int occupants;
        SharedRoom(int units, int occupants) { super(units); this.occupants = occupants; }
        @Override double bill() { return units * 6.0 / occupants; }
        @Override String type() { return "SHARED"; }
    }
    static class AcRoom extends Room {
        AcRoom(int units) { super(units); }
        @Override double bill() { return units * 10.0 + 200.0; }
        @Override String type() { return "AC"; }
    }
    private static Room createRoom(String type, int units, int occupants) {
        return switch (type.toUpperCase(Locale.ROOT)) {
            case "SINGLE" -> new SingleRoom(units);
            case "SHARED" -> new SharedRoom(units, occupants);
            case "AC" -> new AcRoom(units);
            default -> throw new IllegalArgumentException("Unsupported room type: " + type);
        };
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            System.out.println("Enter N followed by room type, units, and occupants for SHARED rooms.");
            return;
        }
        int n = scanner.nextInt();
        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            int units = scanner.nextInt();
            int occupants = type.equalsIgnoreCase("SHARED") ? scanner.nextInt() : 1;
            rooms.add(createRoom(type, units, occupants));
        }
        double total = 0.0;
        for (Room room : rooms) {
            double amount = room.bill();
            System.out.printf(Locale.US, "%s: %.2f%n", room.type(), amount);
            total += amount;
        }
        System.out.printf(Locale.US, "Total: %.2f%n", total);
    }
}
