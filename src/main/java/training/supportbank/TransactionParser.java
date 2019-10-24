package training.supportbank;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TransactionParser {

    public List<Transaction> parseFile() throws IOException, ParseException {
        Path path = Path.of("Transactions2014.csv");
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
