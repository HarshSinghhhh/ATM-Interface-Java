package model;

import java.time.LocalDateTime;

public class Transaction {
    private String type;
    private double amount;
    private LocalDateTime timestamp;

    public String getAccountNumber(){
        return getAccountNumber();
    }

    public Transaction(String accountNumber, String type, double amount, LocalDateTime timestamp) {

        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public Object getAmount() {
        throw new UnsupportedOperationException("Unimplemented method 'getAmount'");
    }

    public Object getType() {

        throw new UnsupportedOperationException("Unimplemented method 'getType'");
    }

    public Object getTimestamp() {

        throw new UnsupportedOperationException("Unimplemented method 'getTimestamp'");
    }

    // getters
}
