package dao;

import model.Account;
import model.Transaction;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class AccountDAO {

    private static final Path ACC_FILE = Paths.get("data/accounts.csv");
    private static final Path TXN_FILE = Paths.get("data/transactions.csv");

    public List<Account> loadAll() throws IOException {
        if (!Files.exists(ACC_FILE)) {
            Files.createFile(ACC_FILE);
        }

        try (Stream<String> lines = Files.lines(ACC_FILE)) {
            return lines
                .filter(line -> line != null && !line.isBlank())
                .map(line -> {
                    String[] arr = line.split(",");
                    if (arr.length < 3) return null; // Skip malformed lines
                    try {
                        return new Account(
                            arr[0],
                            arr[1],
                            Double.parseDouble(arr[2])
                        );
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        }
    }

    public Optional<Account> findByNumber(String accNum) throws IOException {
        return loadAll().stream()
            .filter(a -> a.getAccountNumber().equals(accNum))
            .findFirst();
    }

    public void updateAccount(Account acct) throws IOException {
        List<Account> all = loadAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getAccountNumber().equals(acct.getAccountNumber())) {
                all.set(i, acct);
                break;
            }
        }
        saveAll(all);
    }

    private void saveAll(List<Account> list) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(ACC_FILE)) {
            for (Account a : list) {
                String line = String.format("%s,%s,%.2f",
                    a.getAccountNumber(),
                    a.getHolderName(),
                    a.getBalance()
                );
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public void recordTransaction(Transaction txn) throws IOException {
        if (!Files.exists(TXN_FILE)) {
            Files.createFile(TXN_FILE);
        }

        try (BufferedWriter writer = Files.newBufferedWriter(TXN_FILE, StandardOpenOption.APPEND)) {
            String line = String.format("%s,%s,%.2f,%s",
                txn.getAccountNumber(),
                txn.getType(),
                txn.getAmount(),
                txn.getTimestamp()
            );
            writer.write(line);
            writer.newLine();
        }
    }

    public List<String> loadTransactionHistory(String accNum) throws IOException {
        if (!Files.exists(TXN_FILE)) return Collections.emptyList();

        try (Stream<String> lines = Files.lines(TXN_FILE)) {
            return lines
                .filter(line -> {
                    String[] arr = line.split(",");
                    return arr.length > 0 && arr[0].equals(accNum);
                })
                .collect(Collectors.toList());
        }
    }
}
