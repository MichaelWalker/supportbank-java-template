package training.supportbank.output;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import training.supportbank.Bank;

import java.util.List;
import java.util.Scanner;

public class OutputUserInterface {
    private static final Logger LOGGER = LogManager.getLogger();

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
                LOGGER.info("Printing 'List All'");
                allAccountsOutput.run();
            }
            else if (selectedMethod.startsWith("List [")) {
                String accountName = getNameFromInput(selectedMethod);
                LOGGER.info(String.format("Printing single account for user '%s'", accountName));
                singleAccountOutput.run(accountName);
            }
            else {
                LOGGER.error(String.format("Method '%s' wasn't recognised", selectedMethod));
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
