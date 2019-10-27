package training.supportbank.input.csv;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import training.supportbank.Transaction;
import training.supportbank.input.TransactionModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CsvTransactionModel implements TransactionModel {
    private static final Logger LOGGER = LogManager.getLogger();

    private final Date date;
    private final String from;
    private final String to;
    private final String narrative;
    private final Double amount;

    public CsvTransactionModel(String csvLine) throws ParseException {
        String[] parts = csvLine.split(",");

        date = tryParseDate(parts[0]);
        from = parts[1];
        to = parts[2];
        narrative = parts[3];
        amount = tryParseAmount(parts[4]);
    }

    @Override
    public Transaction toTransaction() {
        return new Transaction(date, from, to, narrative, amount);
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
}
