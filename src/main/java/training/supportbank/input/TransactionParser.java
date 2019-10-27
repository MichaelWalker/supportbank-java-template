package training.supportbank.input;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import training.supportbank.Transaction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TransactionParser {
    private static final Logger LOGGER = LogManager.getLogger();

    public List<Transaction> parseFile(String filename) throws IOException, ParseException {
        List<String> lines = getLinesFromFile(filename);

        List<Transaction> transactions = new ArrayList<>();
        for (int lineNumber=1; lineNumber <= lines.size(); lineNumber++) {
            String line = lines.get(lineNumber - 1);

            if (!line.equals("Date,From,To,Narrative,Amount")) {
                try {
                    transactions.add(TransactionFromCsvLine(line));
                } catch (Exception e) {
                    boolean handled = handleParseException(e, filename, lineNumber);
                    if (!handled) {
                        throw e;
                    }
                }
            }
        }
        return transactions;
    }

    private List<String> getLinesFromFile(String filename) throws IOException {
        try {
            Path path = Path.of(filename);
            return Files.readAllLines(path);
        } catch(Exception e) {
            LOGGER.error(String.format("Failed to read file: %s", filename), e);
            throw e;
        }
    }

    private Transaction TransactionFromCsvLine(String csvLine) throws ParseException {
        String[] parts = csvLine.split(",");

        Date date = tryParseDate(parts[0]);
        String from = parts[1];
        String to = parts[2];
        String description = parts[3];
        Double amount = tryParseAmount(parts[4]);

        return new Transaction(date, from, to, description, amount);
    }

    private Date tryParseDate(String dateString) throws ParseException {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
        } catch (ParseException e) {
            System.out.printf("\033[31mERROR - Failed to convert %s to a date.\n\033[0m", dateString);
            LOGGER.error(String.format("Failed to convert %s to an date.", dateString));
            throw e;
        }
    }

    private Double tryParseAmount(String amountString) throws NumberFormatException {
        try {
            return Double.valueOf(amountString);
        } catch (NumberFormatException e) {
            System.out.printf("\033[31mERROR - Failed to convert %s to an amount.\n\033[0m", amountString);
            LOGGER.error(String.format("Failed to convert %s to an amount.", amountString));
            throw e;
        }
    }

    private boolean handleParseException(Exception e, String filename, Integer lineNumber) {
        LOGGER.error(String.format("Failed to read file %s. Error found on line %d", filename, lineNumber), e);
        System.out.println("Type 'skip' to skip this transaction and continue, or anything else to end the program.");
        Scanner scanner = new Scanner(System.in);
        if (scanner.nextLine().equals("skip")) {
            LOGGER.warn("User opted to skip failed transaction and continue.");
            return true;
        }
        LOGGER.error("User opted to end program after failing to parse transaction.");
        return false;
    }
}
