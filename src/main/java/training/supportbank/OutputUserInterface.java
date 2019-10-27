package training.supportbank;

import java.text.DateFormat;
import java.util.List;
import java.util.Scanner;

public class OutputUserInterface {
    private static final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

    private final Bank bank;

    public OutputUserInterface(Bank bank) {
        this.bank = bank;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        boolean shouldContinue = true;
        while (shouldContinue) {
            System.out.println("Which function would you like to run?");
            String selectedMethod = scanner.nextLine();

            if (selectedMethod.equals("List All")) {
                printAccounts();
            }
            else if (selectedMethod.startsWith("List [")) {
                printAccountDetails(getNameFromInput(selectedMethod));
            }
            else {
                System.out.println("Sorry - it doesn't look like that is a valid function.");
            }
            System.out.println("Would you like to continue?");
            shouldContinue = List.of("y", "yes").contains(scanner.nextLine().toLowerCase());
        }
    }

    private void printAccounts() {
        System.out.printf("%-20s %10s %10s %10s%n", "Name", "In", "Out", "Total");
        System.out.println("-----------------------------------------------------");
        for (Account account : bank.getAccounts()) {
            System.out.printf("%-20s %10.2f %10.2f %10.2f%n",
                    account.getName(),
                    account.getTotalIncomingValue(),
                    account.getTotalOutgoingValue(),
                    account.getBalance()
            );
        }
    }

    private void printAccountDetails(String name) {
        Account account = bank.getAccount(name);

        if (account == null) {
            System.out.println(String.format("Sorry - unable to find an account for %s", name));
        } else {
            System.out.println(name.toUpperCase());
            System.out.printf("Balance: %.2f", account.getBalance());

            System.out.printf("\n\nPayments from %s\n", name);
            printTransactionsFromPerson(account.getOutgoingTransactions());

            System.out.printf("\n\nPayments to %s\n", name);
            printTransactionsToPerson(account.getIncomingTransactions());
        }

    }

    private String getNameFromInput(String input) {
        return input
                .replace("List [", "")
                .replace("]", "");
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
