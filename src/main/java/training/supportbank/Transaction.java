package training.supportbank;

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
}
