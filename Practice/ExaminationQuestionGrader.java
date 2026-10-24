import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExaminationQuestionGrader {
    abstract static class ExamQuestion {
        final String questionText;
        final String correctAnswer;
        final String studentAnswer;
        final double points;
        ExamQuestion(String q, String correct, String answer, double points) {
            this.questionText = q; this.correctAnswer = correct; this.studentAnswer = answer; this.points = points;
        }
        abstract double grade();
        abstract String type();
    }
    static class MultipleChoiceQuestion extends ExamQuestion {
        MultipleChoiceQuestion(String q, String c, String a, double p) { super(q, c, a, p); }
        @Override double grade() { return studentAnswer.equals(correctAnswer) ? points : 0.0; }
        @Override String type() { return "MCQ"; }
    }
    static class TrueFalseQuestion extends ExamQuestion {
        TrueFalseQuestion(String q, String c, String a, double p) { super(q, c, a, p); }
        @Override double grade() { return studentAnswer.equals(correctAnswer) ? points : 0.0; }
        @Override String type() { return "TF"; }
    }
    static class EssayQuestion extends ExamQuestion {
        EssayQuestion(String q, String c, String a, double p) { super(q, c, a, p); }
        @Override double grade() {
            String answerLower = studentAnswer.toLowerCase(Locale.ROOT);
            int found = 0;
            for (String keyword : correctAnswer.split(",")) {
                String cleaned = keyword.trim().toLowerCase(Locale.ROOT);
                if (!cleaned.isEmpty() && answerLower.contains(cleaned)) found++;
            }
            if (found >= 2) return points * 0.75;
            if (found == 1) return points * 0.50;
            return 0.0;
        }
        @Override String type() { return "ESSAY"; }
    }

    private static String nextNonEmptyLine(BufferedReader reader) throws Exception {
        String line;
        while ((line = reader.readLine()) != null) if (!line.isBlank()) return line.trim();
        return null;
    }

    private static ExamQuestion parseQuestion(String line) {
        int typeEnd = line.indexOf(' ');
        if (typeEnd < 0) throw new IllegalArgumentException("Malformed question record: " + line);
        String type = line.substring(0, typeEnd).trim().toUpperCase(Locale.ROOT);
        Matcher matcher = Pattern.compile("\"([^\"]*)\"").matcher(line);
        List<String> fields = new ArrayList<>();
        while (matcher.find()) fields.add(matcher.group(1));
        if (fields.size() != 3) throw new IllegalArgumentException("Expected three quoted fields: " + line);
        String suffix = line.substring(line.lastIndexOf('"') + 1).trim();
        double points = Double.parseDouble(suffix);
        return switch (type) {
            case "MCQ" -> new MultipleChoiceQuestion(fields.get(0), fields.get(1), fields.get(2), points);
            case "TF" -> new TrueFalseQuestion(fields.get(0), fields.get(1), fields.get(2), points);
            case "ESSAY" -> new EssayQuestion(fields.get(0), fields.get(1), fields.get(2), points);
            default -> throw new IllegalArgumentException("Unsupported question type: " + type);
        };
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String first = nextNonEmptyLine(reader);
        if (first == null) {
            System.out.println("Enter N and one question record per line, using quoted text fields.");
            return;
        }
        int n = Integer.parseInt(first);
        List<ExamQuestion> questions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String line = nextNonEmptyLine(reader);
            if (line == null) throw new IllegalArgumentException("Missing question record.");
            questions.add(parseQuestion(line));
        }
        double total = 0.0;
        for (ExamQuestion question : questions) {
            double score = question.grade();
            System.out.printf(Locale.US, "%s: %.2f%n", question.type(), score);
            total += score;
        }
        System.out.printf(Locale.US, "Total Score: %.2f%n", total);
    }
}
