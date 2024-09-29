public class Income extends Entry {

    public Income(double amount, String category, String note) {
        super(amount, category, note);
    }

    @Override
    public String toString() {
        return "+++   " + super.toString();
    }

    public static Income inputIncome() {
        Entry entry = inputEntry();
        return new Income(entry.getAmount(), entry.getCategory(), entry.getNote());
    }
}
