package training.supportbank;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bank {
    private final List<Account> accounts;

    public Bank(List<Transaction> transactions) {
        accounts = new ArrayList<>();

        for (String name : getUniqueNames(transactions)) {
            accounts.add(createAccount(name, transactions));
        }
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    private Set<String> getUniqueNames(List<Transaction> transactions) {
        Set<String> uniqueNames = new HashSet<>();

        for (Transaction transaction : transactions) {
            uniqueNames.add(transaction.getTo());
            uniqueNames.add(transaction.getFrom());
        }

        return uniqueNames;
    }

    private Account createAccount(String name, List<Transaction> allTransactions) {
        Account account = new Account(name);

        for (Transaction transaction : allTransactions) {
            if (transaction.getFrom().equals(name)) {
                account.addOutgoingTransaction(transaction);
            }
            if (transaction.getTo().equals(name)) {
                account.addIncomingTransaction(transaction);
            }
        }

        return account;
    }

    public Account getAccount(String name) {
        for (Account account : accounts) {
            if (account.getName().equals(name)) {
                return account;
            }
        }
        return null;
    }
}
