package service;

import model.Account;
import util.FileUtil;
import java.util.*;

public class ATMService {
    private static List<Account> accounts;

    public ATMService() {
        accounts = FileUtil.loadAccounts();
    }

    public static Account authenticate(String accNum, String pin) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accNum) && acc.getPin().equals(pin)) {
                return acc;
            }
        }
        return null;
    }

    public static Account deposit(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
        FileUtil.saveAccounts(accounts);
        return account;
    }

    public static boolean withdraw(Account account, double amount) {
        if (amount > account.getBalance()) return false;
        account.setBalance(account.getBalance() - amount);
        FileUtil.saveAccounts(accounts);
        return true;
    }

    public static double checkBalance(Account account) {
        return account.getBalance();
    }

    public static Object showTransactionHistory(Account acc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showTransactionHistory'");
    }
}
