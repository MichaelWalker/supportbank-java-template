package training.supportbank.output;

import training.supportbank.Account;
import training.supportbank.Bank;
import training.supportbank.Transaction;

import java.text.DateFormat;
import java.util.List;

public class SingleAccountOutput {
    private static final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

    private final Bank bank;

    public SingleAccountOutput(Bank bank) {
        this.bank = bank;
    }

    public void run(String accountName) {
        Account account = bank.getAccount(accountName);

        if (account == null) {
            System.out.println(String.format("Sorry - unable to find an account for %s", accountName));
        } else {
            System.out.println(accountName.toUpperCase());
            System.out.printf("Balance: %.2f", account.getBalance());

            System.out.printf("\n\nPayments from %s\n", accountName);
            printTransactionsFromPerson(account.getOutgoingTransactions());

            System.out.printf("\n\nPayments to %s\n", accountName);
            printTransactionsToPerson(account.getIncomingTransactions());
        }
    }

    private void printTransactionsToPerson(List<Transaction> transactions) {
        System.out.printf("%-15s %-15s %-40s %10s\n", "Date", "From", "Description", "Amount");
        System.out.println("-----------------------------------------------------------------------------------");
        for (Transaction transaction : transactions) {
            System.out.printf("%-15s %-15s %-40s %10.2f\n",
                    dateFormat.format(transaction.getDate()),
                    transaction.getFrom(),
                    transaction.getDescription(),
                    transaction.getAmount()
            );
        }
    }

    private void printTransactionsFromPerson(List<Transaction> transactions) {
        System.out.printf("%-15s %-15s %-40s %10s\n", "Date", "To", "Description", "Amount");
        System.out.println("-----------------------------------------------------------------------------------");
        for (Transaction transaction : transactions) {
            System.out.printf("%-15s %-15s %-40s %10.2f\n",
                    dateFormat.format(transaction.getDate()),
                    transaction.getTo(),
                    transaction.getDescription(),
                    transaction.getAmount()
            );
        }
    }
}
