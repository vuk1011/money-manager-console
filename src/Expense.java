public class Expense extends Entry {

    public Expense(double amount, String category, String note) {
        super(amount, category, note);
    }

    @Override
    public String toString() {
        return "---   " + super.toString();
    }

    public static Expense inputExpense() {
        Entry entry = inputEntry();
        return new Expense(entry.getAmount(), entry.getCategory(), entry.getNote());
    }
}
