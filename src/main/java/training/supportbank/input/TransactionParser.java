package training.supportbank.input;

import training.supportbank.Transaction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TransactionParser {

    public List<Transaction> parseFile(String filename) throws IOException, ParseException {
        Path path = Path.of(filename);
        List<String> lines = Files.readAllLines(path);

        List<Transaction> transactions = new ArrayList<>();
        for (String line : lines) {
            if (!line.equals("Date,From,To,Narrative,Amount")) {
                transactions.add(Transaction.fromCsvLine(line));
            }
        }
        return transactions;
    }
}
