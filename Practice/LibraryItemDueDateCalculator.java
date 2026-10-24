import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LibraryItemDueDateCalculator {
    private static final LocalDate CURRENT_DATE = LocalDate.of(2023, 10, 26);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    abstract static class LibraryItem {
        final String title;
        LibraryItem(String title) { this.title = title; }
        abstract int loanDays();
        LocalDate dueDate() { return CURRENT_DATE.plusDays(loanDays()); }
    }
    static class Book extends LibraryItem {
        Book(String title) { super(title); }
        @Override int loanDays() { return 14; }
    }
    static class DVD extends LibraryItem {
        DVD(String title) { super(title); }
        @Override int loanDays() { return 7; }
    }
    static class Magazine extends LibraryItem {
        Magazine(String title) { super(title); }
        @Override int loanDays() { return 3; }
    }

    private static LibraryItem createItem(String type, String title) {
        return switch (type.toUpperCase()) {
            case "BOOK" -> new Book(title);
            case "DVD" -> new DVD(title);
            case "MAGAZINE" -> new Magazine(title);
            default -> throw new IllegalArgumentException("Unsupported item type: " + type);
        };
    }

    private static String nextNonEmptyLine(BufferedReader reader) throws Exception {
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.isBlank()) return line.trim();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String first = nextNonEmptyLine(reader);
        if (first == null) {
            System.out.println("Enter N, followed by item type and quoted title on each line.");
            return;
        }
        int n = Integer.parseInt(first);
        List<LibraryItem> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String line = nextNonEmptyLine(reader);
            if (line == null) throw new IllegalArgumentException("Missing item record.");
            int split = line.indexOf(' ');
            String type = line.substring(0, split);
            String title = line.substring(split + 1).trim();
            if (title.startsWith("\"") && title.endsWith("\"") && title.length() >= 2) {
                title = title.substring(1, title.length() - 1);
            }
            items.add(createItem(type, title));
        }
        for (LibraryItem item : items) {
            System.out.println(item.title + ": " + item.dueDate().format(FORMATTER));
        }
    }
}
