import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class App {
    private double balance;
    private ArrayList<Entry> entries;
    private static final String[] actions = {
            "1) Enter Expense",
            "2) Enter Income",
            "3) View History",
            "4) View Balance",
            "5) Delete Data",
            "6) Exit"
    };
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy.");

    public App() {
        this.balance = 0.0;
        this.entries = new ArrayList<>();
    }

    public double getBalance() {
        return (double) Math.round(balance * 100) / 100;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void startApp() {
        if (new File("data").mkdir()) return;
        File balanceFile = new File("data\\balance.dat");
        File entriesFile = new File("data\\entries.dat");

        try {
            FileInputStream balanceFileStream = new FileInputStream(balanceFile);
            FileInputStream entriesFileStream = new FileInputStream(entriesFile);

            ObjectInputStream balanceObjectStream = new ObjectInputStream(balanceFileStream);
            ObjectInputStream entriesObjectStream = new ObjectInputStream(entriesFileStream);

            balance = balanceObjectStream.readDouble();
            entries = (ArrayList<Entry>) entriesObjectStream.readObject();

            balanceObjectStream.close();
            entriesObjectStream.close();

            balanceFileStream.close();
            entriesFileStream.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeApp() {
        File balanceFile = new File("data\\balance.dat");
        File entriesFile = new File("data\\entries.dat");

        try {
            balanceFile.createNewFile();
            entriesFile.createNewFile();

            FileOutputStream balanceFileStream = new FileOutputStream(balanceFile);
            FileOutputStream entriesFileStream = new FileOutputStream(entriesFile);

            ObjectOutputStream balanceObjectStream = new ObjectOutputStream(balanceFileStream);
            ObjectOutputStream entriesObjectStream = new ObjectOutputStream(entriesFileStream);

            balanceObjectStream.writeDouble(balance);
            entriesObjectStream.writeObject(entries);

            balanceObjectStream.close();
            entriesObjectStream.close();

            balanceFileStream.close();
            entriesFileStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        startApp();
        System.out.println("Welcome to Money Manager!");
        int option;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("-------------------------------------------------------------------");
            Arrays.stream(actions).forEach(System.out::println);
            System.out.print("Choose action: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    Expense expense = Expense.inputExpense();
                    entries.addFirst(expense);
                    setBalance(balance - expense.getAmount());
                    break;
                case 2:
                    Income income = Income.inputIncome();
                    entries.addFirst(income);
                    setBalance(balance + income.getAmount());
                    break;
                case 3:
                    entries.forEach(System.out::println);
                    break;
                case 4:
                    System.out.println("Balance is  " + Entry.currency + getBalance());
                    break;
                case 5:
                    entries.clear();
                    balance = 0.0;
                    break;
                default:
                    System.out.println("Exiting...");
                    closeApp();
                    return;
            }
        }
    }
}
