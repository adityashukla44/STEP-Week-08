import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class StreamingPlanRenewalReminder {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;
    abstract static class Plan {
        final String name;
        final LocalDate startDate;
        Plan(String name, LocalDate startDate) { this.name = name; this.startDate = startDate; }
        abstract int validityDays();
        LocalDate renewalDate() { return startDate.plusDays(validityDays()); }
    }
    static class BasicPlan extends Plan {
        BasicPlan(String name, LocalDate date) { super(name, date); }
        @Override int validityDays() { return 30; }
    }
    static class StandardPlan extends Plan {
        StandardPlan(String name, LocalDate date) { super(name, date); }
        @Override int validityDays() { return 90; }
    }
    static class PremiumPlan extends Plan {
        PremiumPlan(String name, LocalDate date) { super(name, date); }
        @Override int validityDays() { return 365; }
    }
    private static Plan createPlan(String type, String name, LocalDate date) {
        return switch (type.toUpperCase(Locale.ROOT)) {
            case "BASIC" -> new BasicPlan(name, date);
            case "STANDARD" -> new StandardPlan(name, date);
            case "PREMIUM" -> new PremiumPlan(name, date);
            default -> throw new IllegalArgumentException("Unsupported plan type: " + type);
        };
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            System.out.println("Enter N followed by plan type, subscriber name, and start date (YYYY-MM-DD).");
            return;
        }
        int n = scanner.nextInt();
        List<Plan> plans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String name = scanner.next();
            LocalDate date = LocalDate.parse(scanner.next(), DATE_FORMAT);
            plans.add(createPlan(type, name, date));
        }
        for (Plan plan : plans) {
            System.out.println(plan.name + ": " + plan.renewalDate().format(DATE_FORMAT));
        }
    }
}
