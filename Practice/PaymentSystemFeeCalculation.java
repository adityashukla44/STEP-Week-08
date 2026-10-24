import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class PaymentSystemFeeCalculation {
    abstract static class PaymentMethod {
        final double amount;
        PaymentMethod(double amount) { this.amount = amount; }
        abstract double finalAmount();
        abstract String type();
    }
    static class CardPayment extends PaymentMethod {
        CardPayment(double amount) { super(amount); }
        @Override double finalAmount() { return amount * 1.02; }
        @Override String type() { return "CARD"; }
    }
    static class WalletPayment extends PaymentMethod {
        WalletPayment(double amount) { super(amount); }
        @Override double finalAmount() { return amount * 1.01; }
        @Override String type() { return "WALLET"; }
    }
    static class BankTransferPayment extends PaymentMethod {
        BankTransferPayment(double amount) { super(amount); }
        @Override double finalAmount() { return amount; }
        @Override String type() { return "BANKTRANSFER"; }
    }

    private static PaymentMethod createPayment(String type, double amount) {
        return switch (type.toUpperCase(Locale.ROOT)) {
            case "CARD" -> new CardPayment(amount);
            case "WALLET" -> new WalletPayment(amount);
            case "BANKTRANSFER" -> new BankTransferPayment(amount);
            default -> throw new IllegalArgumentException("Unsupported payment type: " + type);
        };
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            System.out.println("Enter N, followed by payment type and amount on each line.");
            return;
        }
        int n = scanner.nextInt();
        List<PaymentMethod> payments = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            double amount = scanner.nextDouble();
            payments.add(createPayment(type, amount));
        }
        double total = 0.0;
        for (PaymentMethod payment : payments) {
            double adjusted = payment.finalAmount();
            System.out.printf(Locale.US, "%s: %.2f%n", payment.type(), adjusted);
            total += adjusted;
        }
        System.out.printf(Locale.US, "Total: %.2f%n", total);
    }
}
