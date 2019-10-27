package training.supportbank.input;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import training.supportbank.Transaction;

import java.util.List;
import java.util.Scanner;

public class InputUserInterface {
    private static final Logger LOGGER = LogManager.getLogger();

    private final Scanner scanner;
    private final TransactionParser transactionParser;

    public InputUserInterface() {
        scanner = new Scanner(System.in);
        transactionParser = new TransactionParser();
    }

    public List<Transaction> run() {
        List<Transaction> transactions = null;

        System.out.println("Welcome to Support Bank.\n");
        while (transactions == null) {
            try {
                System.out.println("Please enter a filename:");
                String filename = scanner.nextLine();
                LOGGER.info("Attempting to parse file: " + filename);
                transactions = transactionParser.parseFile(filename);
            } catch (Exception e) {
                LOGGER.error("Failed to process file.");
                System.out.println("Sorry - we weren't able to process that file. Would you like to try another file? (y/n)");
                if (scanner.nextLine().equals("n")) {
                    LOGGER.info("User opted to end the program.");
                    System.exit(0);
                } else {
                    LOGGER.info("User opted to retry with another file.");
                }
            }
        }

        System.out.println("\033[32mSuccess - File read completed.\033[0m\n");
        return transactions;
    }
}
