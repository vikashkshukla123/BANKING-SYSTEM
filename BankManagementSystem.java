
import java.util.*;

// Class to represent a transaction
class Transaction {
    String type;
    double amount;
    Date date;

    Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return date + " - " + type + ": Rs." + amount;
    }
}

// Class to represent a bank account
class BankAccount {
    private String accountNumber;
    private String password;
    private double balance;
    private List<Transaction> transactions;
    private double interestRate = 0.03; // 3% annual interest

    public BankAccount(String accountNumber, String password, double initialBalance) {
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }

    public boolean authenticate(String accNum, String pass) {
        return this.accountNumber.equals(accNum) && this.password.equals(pass);
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("Deposit", amount));
        System.out.println("Deposited Rs." + amount);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactions.add(new Transaction("Withdrawal", amount));
            System.out.println("Withdrawn Rs." + amount);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    public void checkBalance() {
        System.out.println("Current Balance: Rs." + balance);
    }

    public void showTransactions() {
        System.out.println("Transaction History:");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    public void calculateInterest(int years) {
        double interest = balance * interestRate * years;
        System.out.println("Interest for " + years + " year(s): Rs." + interest);
    }
}

// Main System Class
public class BankManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Creating a sample account
        BankAccount account = new BankAccount("12345", "pass123", 1000);

        System.out.println("=== Welcome to Bank Management System ===");
        System.out.print("Enter Account Number: ");
        String accNum = sc.nextLine();
        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        if (account.authenticate(accNum, pass)) {
            int choice;
            do {
                System.out.println("\n--- Menu ---");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Check Balance");
                System.out.println("4. Transaction History");
                System.out.println("5. Calculate Interest");
                System.out.println("6. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter deposit amount: ");
                        double dep = sc.nextDouble();
                        account.deposit(dep);
                        break;
                    case 2:
                        System.out.print("Enter withdrawal amount: ");
                        double wd = sc.nextDouble();
                        account.withdraw(wd);
                        break;
                    case 3:
                        account.checkBalance();
                        break;
                    case 4:
                        account.showTransactions();
                        break;
                    case 5:
                        System.out.print("Enter number of years: ");
                        int years = sc.nextInt();
                        account.calculateInterest(years);
                        break;
                    case 6:
                        System.out.println("Exiting... Thank you!");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } while (choice != 6);
        } else {
            System.out.println("Authentication Failed! Wrong account number or password.");
        }

        sc.close();
    }
}



