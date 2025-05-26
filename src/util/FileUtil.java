package util;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import model.Account;

public class FileUtil {
    private static final String ACCOUNTS_FILE = "data/accounts.csv";

    public static List<Account> loadAccounts() {
        List<Account> accounts = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(ACCOUNTS_FILE));
            for (String line : lines) {
                String[] parts = line.split(",");
                accounts.add(new Account(parts[0], parts[1], Double.parseDouble(parts[2])));
            }
        } catch (IOException e) {
            System.out.println("Error reading accounts file.");
        }
        return accounts;
    }

    public static void saveAccounts(List<Account> accounts) {
        try (PrintWriter writer = new PrintWriter(ACCOUNTS_FILE)) {
            for (Account acc : accounts) {
                writer.println(acc.getAccountNumber() + "," + acc.getPin() + "," + acc.getBalance());
            }
        } catch (IOException e) {
            System.out.println("Error writing accounts file.");
        }
    }
}
