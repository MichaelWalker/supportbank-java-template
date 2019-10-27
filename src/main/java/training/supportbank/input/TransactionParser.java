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
        List<String> lines;

        try {
            Path path = Path.of(filename);
            lines = Files.readAllLines(path);
        } catch(Exception e) {
            LOGGER.error(String.format("Failed to read file: %s", filename), e);
            throw e;
        }

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

    private Transaction TransactionFromCsvLine(String csvLine) throws ParseException {
        String[] parts = csvLine.split(",");

        Date date;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(parts[0]);
        } catch (ParseException e) {
            System.out.printf("\033[31mERROR - Failed to convert %s to a date.\n\033[0m", parts[4]);
            LOGGER.error(String.format("Failed to convert %s to an date.", parts[0]));
            throw e;
        }
        String from = parts[1];
        String to = parts[2];
        String description = parts[3];

        Double amount;
        try {
            amount = Double.valueOf(parts[4]);
        } catch (NumberFormatException e) {
            System.out.printf("\033[31mERROR - Failed to convert %s to an amount.\n\033[0m", parts[4]);
            LOGGER.error(String.format("Failed to convert %s to an amount.", parts[4]));
            throw e;
        }

        return new Transaction(date, from, to, description, amount);
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
