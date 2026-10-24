import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class FestivalBonusCalculator {
    abstract static class Employee {
        final String name;
        final double monthlySalary;
        Employee(String name, double salary) { this.name = name; this.monthlySalary = salary; }
        abstract double bonus();
    }
    static class FullTimeEmployee extends Employee {
        FullTimeEmployee(String name, double salary) { super(name, salary); }
        @Override double bonus() { return monthlySalary * 0.10; }
    }
    static class PartTimeEmployee extends Employee {
        PartTimeEmployee(String name, double salary) { super(name, salary); }
        @Override double bonus() { return monthlySalary * 0.05; }
    }
    static class Intern extends Employee {
        Intern(String name, double salary) { super(name, salary); }
        @Override double bonus() { return 2000.0; }
    }
    private static Employee createEmployee(String type, String name, double salary) {
        return switch (type.toUpperCase(Locale.ROOT)) {
            case "FULLTIME" -> new FullTimeEmployee(name, salary);
            case "PARTTIME" -> new PartTimeEmployee(name, salary);
            case "INTERN" -> new Intern(name, salary);
            default -> throw new IllegalArgumentException("Unsupported employee type: " + type);
        };
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            System.out.println("Enter N followed by employee type, name, and monthly salary.");
            return;
        }
        int n = scanner.nextInt();
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            employees.add(createEmployee(scanner.next(), scanner.next(), scanner.nextDouble()));
        }
        double total = 0.0;
        for (Employee employee : employees) {
            double bonus = employee.bonus();
            System.out.printf(Locale.US, "%s: %.2f%n", employee.name, bonus);
            total += bonus;
        }
        System.out.printf(Locale.US, "Total Bonus: %.2f%n", total);
    }
}
