package training.supportbank.input.csv;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import training.supportbank.input.TransactionModel;
import training.supportbank.input.TransactionParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvTransactionParser implements TransactionParser {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public boolean canHandleFileType(String fileType) {
        return fileType.equals("csv");
    }

    @Override
    public List<TransactionModel> parseFile(String filename) throws IOException, ParseException {
        List<String> lines = getLinesFromFile(filename);

        List<TransactionModel> transactions = new ArrayList<>();
        for (int lineNumber=1; lineNumber <= lines.size(); lineNumber++) {
            String line = lines.get(lineNumber - 1);

            if (!line.equals("Date,From,To,Narrative,Amount")) {
                try {
                    transactions.add(new CsvTransactionModel(line));
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
        } catch(NoSuchFileException e) {
            LOGGER.error(String.format("File '%s' does not exist", filename));
            System.out.printf("\033[31mUnable to find file: %s\n\033[0m", filename);
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
