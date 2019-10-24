package training.supportbank;

import java.util.List;

public class OutputUserInterface {
    private final Bank bank;

    public OutputUserInterface(Bank bank) {
        this.bank = bank;
    }

    public void run() {
        printAccounts(bank.getAccounts());
    }

    private void printAccounts(List<Account> accounts) {
        for (Account account : accounts) {
            System.out.printf("%-20s %-10.2f %-10.2f %-10.2f%n",
                    account.getName(),
                    account.getTotalIncomingValue(),
                    account.getTotalOutgoingValue(),
                    account.getBalance()
            );
        }
    }
}
