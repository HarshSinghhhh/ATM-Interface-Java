package ui;

import dao.ConsoleHelper;
import model.Account;
import service.ATMService;

public class ATMInterface {
    public static void showMenu() {
        String accNo = ConsoleHelper.readString("Enter Account Number: ");
        String pin = ConsoleHelper.readString("Enter PIN: ");

        Account acc = ATMService.authenticate(accNo, pin);
        if (acc == null) {
            ConsoleHelper.printLine("Authentication failed!");
            return;
        }

        int choice;
        do {
            ConsoleHelper.printLine("\n1. Balance Inquiry");
            ConsoleHelper.printLine("2. Cash Withdrawal");
            ConsoleHelper.printLine("3. Deposit");
            ConsoleHelper.printLine("4. Transaction History");
            ConsoleHelper.printLine("5. Exit");

            choice = ConsoleHelper.readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> ATMService.checkBalance(acc);
                case 2 -> ATMService.withdraw(acc, ConsoleHelper.readDouble("Enter amount: "));
                case 3 -> ATMService.deposit(acc, ConsoleHelper.readDouble("Enter amount: "));
                case 4 -> ATMService.showTransactionHistory(acc);
                case 5 -> ConsoleHelper.printLine("Thank you! Card returned.");
                default -> ConsoleHelper.printLine("Invalid option.");
            }
        } while (choice!=5);
    }
}