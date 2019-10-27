package training.supportbank.input;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import training.supportbank.Transaction;

import java.util.List;
import java.util.Scanner;

public class InputUserInterface {
    private static final Logger LOGGER = LogManager.getLogger();

    private final Scanner scanner;
    private final List<TransactionParser> transactionParsers;

    public InputUserInterface() {
        scanner = new Scanner(System.in);
        transactionParsers = List.of(
                new CsvTransactionParser(),
                new JsonTransactionParser()
        );
    }

    public List<Transaction> run() {
        System.out.println("Welcome to Support Bank.\n");

        List<Transaction> transactions = null;
        while(transactions == null) {
            String filename = getFilenameFromUser();
            String fileType = getFileExtension(filename);
            TransactionParser transactionParser = getTransactionParser(fileType);

            if (transactionParser != null) {
                transactions = tryParseFile(transactionParser, filename);
            }
        }

        System.out.println("\033[32mSuccess - File read completed.\033[0m\n");
        return transactions;
    }

    private String getFilenameFromUser() {
        System.out.println("Please enter a filename:");
        return scanner.nextLine();
    }

    private String getFileExtension(String filename) {
        int index = filename.lastIndexOf(".");
        if (index > 0) {
            return filename.substring(index + 1);
        }
        return "";
    }

    private TransactionParser getTransactionParser(String fileType) {
        for (TransactionParser parser : transactionParsers) {
            if (parser.canHandleFileType(fileType)) {
                LOGGER.info(String.format("Using logger: %s", parser.getClass()));
                return parser;
            }
        }
        System.out.printf("\033[31mUnable to find a parser for fileType: %s\n\n\033[0m", fileType);
        return null;
    }

    private List<Transaction> tryParseFile(TransactionParser parser, String filename) {
        try {
            LOGGER.info("Attempting to parse file: " + filename);
            return parser.parseFile(filename);
        } catch (Exception e) {
            handleParseException();
            return null;
        }
    }

    private void handleParseException() {
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
