import java.util.Scanner;

class Account {
    private double balance;

    public Account(double initialBalance) {
        this.balance = initialBalance;
    }

    public void checkBalance() {
        System.out.println("💰 Current Balance: ₹" + balance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ ₹" + amount + " deposited successfully.");
        } else {
            System.out.println("❌ Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("✅ ₹" + amount + " withdrawn successfully.");
        } else {
            System.out.println("❌ Insufficient balance or invalid amount.");
        }
    }
}

public class ATMSimulator {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Account account = new Account(1000.0); // Initial balance
            int choice;

            System.out.println("🏧 Welcome to Simple ATM Simulator");

            do {
                System.out.println("\n========= MENU =========");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Exit");
                System.out.print("Choose an option (1-4): ");

                while (!scanner.hasNextInt()) {
                    System.out.print("❌ Invalid input! Enter number 1-4: ");
                    scanner.next();
                }

                choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> account.checkBalance();
                    case 2 -> {
                        System.out.print("Enter amount to deposit: ₹");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                    }
                    case 3 -> {
                        System.out.print("Enter amount to withdraw: ₹");
                        double withdrawAmount = scanner.nextDouble();
                        account.withdraw(withdrawAmount);
                    }
                    case 4 -> System.out.println("👋 Thank you for using ATM. Goodbye!");
                    default -> System.out.println("❌ Invalid option. Try again.");
                }

            } while (choice != 4);
        }
    }
}
