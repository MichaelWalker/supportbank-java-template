package training.supportbank;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String name;
    private final List<Transaction> incomingTransactions;
    private final List<Transaction> outgoingTransactions;

    public Account(String name) {
        this.name = name;
        incomingTransactions = new ArrayList<>();
        outgoingTransactions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addIncomingTransaction(Transaction transaction) {
        incomingTransactions.add(transaction);
    }

    public List<Transaction> getIncomingTransactions() {
        return incomingTransactions;
    }

    public void addOutgoingTransaction(Transaction transaction) {
        outgoingTransactions.add(transaction);
    }

    public List<Transaction> getOutgoingTransactions() {
        return outgoingTransactions;
    }
}
