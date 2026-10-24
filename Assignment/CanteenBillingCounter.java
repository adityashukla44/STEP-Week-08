import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CanteenBillingCounter {
    abstract static class Customer {
        final double bill;
        Customer(double bill) { this.bill = bill; }
        abstract double finalAmount();
        abstract String type();
    }
    static class Student extends Customer {
        Student(double bill) { super(bill); }
        @Override double finalAmount() { return bill * 0.90; }
        @Override String type() { return "STUDENT"; }
    }
    static class Staff extends Customer {
        Staff(double bill) { super(bill); }
        @Override double finalAmount() { return bill * 0.95; }
        @Override String type() { return "STAFF"; }
    }
    static class Guest extends Customer {
        Guest(double bill) { super(bill); }
        @Override double finalAmount() { return bill + 10.0; }
        @Override String type() { return "GUEST"; }
    }
    private static Customer createCustomer(String type, double amount) {
        return switch (type.toUpperCase(Locale.ROOT)) {
            case "STUDENT" -> new Student(amount);
            case "STAFF" -> new Staff(amount);
            case "GUEST" -> new Guest(amount);
            default -> throw new IllegalArgumentException("Unsupported customer type: " + type);
        };
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            System.out.println("Enter N followed by customer type and bill amount for each bill.");
            return;
        }
        int n = scanner.nextInt();
        List<Customer> bills = new ArrayList<>();
        for (int i = 0; i < n; i++) bills.add(createCustomer(scanner.next(), scanner.nextDouble()));
        double total = 0.0;
        for (Customer customer : bills) {
            double amount = customer.finalAmount();
            System.out.printf(Locale.US, "%s: %.2f%n", customer.type(), amount);
            total += amount;
        }
        System.out.printf(Locale.US, "Total: %.2f%n", total);
    }
}
