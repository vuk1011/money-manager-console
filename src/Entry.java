import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Entry implements Serializable {
    public static final String currency = "$";
    private double amount;
    private String category;
    private String note;
    private final LocalDateTime timeAdded;

    public Entry(double amount, String category, String note) {
        this.amount = amount;
        this.category = category;
        this.note = note;
        this.timeAdded = LocalDateTime.now();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getTimeAdded() {
        return timeAdded;
    }

    public String getFormattedTimeAdded() {
        return getTimeAdded().format(App.formatter);
    }

    @Override
    public String toString() {
        String s = "";
        s += getFormattedTimeAdded() + "  " + currency + getAmount() + " [" + getCategory() + "]";
        s += (getNote().isBlank()) ? "" : " (" + getNote() + ")";
        return s;
    }

    public static Entry inputEntry() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\tInputting an entry...");
        System.out.print("\t\tamount: ");
        double amount = scanner.nextDouble();
        System.out.print("\t\tcategory: ");
        String category = scanner.next();
        System.out.print("\t\tnote: ");
        String note = scanner.next();

        return new Entry(amount, category, note);
    }
}
