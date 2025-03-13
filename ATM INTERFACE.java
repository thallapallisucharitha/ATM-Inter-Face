import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ATM {
    private double balance;
    private final String userPin = "5683";
    private List<String> transactionHistory;

    public ATM(double initialBalance) {
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public boolean authenticateUser() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your PIN: ");
        String enteredPin = sc.nextLine();
        return enteredPin.equals(userPin);
    }

    public void showMenu() {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("4. Transfer Money");
            System.out.println("5. Check Balance");
            System.out.println("6. Quit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    showTransactionHistory();
                    break;
                case 2:
                    withdrawMoney();
                    break;
                case 3:
                    depositMoney();
                    break;
                case 4:
                    transferMoney();
                    break;
                case 5:
                    checkBalnace();
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close();
    }

    private void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    private void depositMoney() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: ₹" + amount);
            System.out.println("₹" + amount + " deposited successfully.");
        } else {
            System.out.println("Invalid amount. Please enter a positive value.");
        }
    }

    private void withdrawMoney() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter amount to withdraw: ");
        double amount = sc.nextDouble();
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: ₹" + amount);
            System.out.println("v" + amount + " withdrawn successfully.");
        } else {
            System.out.println("Invalid amount or insufficient balance.");
        }
    }

    private void transferMoney() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter recipient's account number: ");
        String accountNumber = sc.next();
        System.out.print("Enter amount to transfer: ");
        double amount = sc.nextDouble();
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Transferred ₹" + amount + " to account " + accountNumber);
            System.out.println("₹" + amount + " transferred successfully to account " + accountNumber);
        } else {
            System.out.println("Invalid amount or insufficient balance.");
        }
    }

    private void checkBalnace() {
        System.out.println("Present Balace : " + balance);
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        ATM atm = new ATM(10000);

        if (atm.authenticateUser()) {
            atm.showMenu();
        } else {
            System.out.println("Incorrect PIN. Access denied.");
        }
    }
}