package training.supportbank;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private final Date date;
    private final String from;
    private final String to;
    private final String description;
    private final Double amount;

    public Transaction(Date date, String from, String to, String description, Double amount) {
        this.date = date;
        this.from = from;
        this.to = to;
        this.description = description;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDescription() {
        return description;
    }

    public Double getAmount() {
        return amount;
    }

    public static Transaction fromCsvLine(String csvLine) throws ParseException {
        String[] parts = csvLine.split(",");

        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(parts[0]);
        String from = parts[1];
        String to = parts[2];
        String description = parts[3];
        Double amount = Double.valueOf(parts[4]);

        return new Transaction(date, from, to, description, amount);
    }
}
