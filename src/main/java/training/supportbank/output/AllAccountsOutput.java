package training.supportbank.output;

import training.supportbank.Account;
import training.supportbank.Bank;

public class AllAccountsOutput {
    private final Bank bank;

    public AllAccountsOutput(Bank bank) {
        this.bank = bank;
    }

    public void run() {
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
}
