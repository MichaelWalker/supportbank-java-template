package training.supportbank;

import java.util.List;
import java.util.Scanner;

public class OutputUserInterface {
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
}
