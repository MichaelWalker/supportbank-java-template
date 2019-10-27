package training.supportbank.output;

import training.supportbank.Bank;

import java.util.List;
import java.util.Scanner;

public class OutputUserInterface {
    private final AllAccountsOutput allAccountsOutput;
    private final SingleAccountOutput singleAccountOutput;

    public OutputUserInterface(Bank bank) {
        allAccountsOutput = new AllAccountsOutput(bank);
        singleAccountOutput = new SingleAccountOutput(bank);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        boolean shouldContinue = true;
        while (shouldContinue) {
            System.out.println("Which function would you like to run?");
            String selectedMethod = scanner.nextLine();

            if (selectedMethod.equals("List All")) {
                allAccountsOutput.run();
            }
            else if (selectedMethod.startsWith("List [")) {
                singleAccountOutput.run(getNameFromInput(selectedMethod));
            }
            else {
                System.out.println("Sorry - it doesn't look like that is a valid function.");
            }
            System.out.println("Would you like to continue?");
            shouldContinue = List.of("y", "yes").contains(scanner.nextLine().toLowerCase());
        }
    }

    private String getNameFromInput(String input) {
        return input
                .replace("List [", "")
                .replace("]", "");
    }
}
