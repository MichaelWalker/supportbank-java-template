package training.supportbank.input;

import training.supportbank.Transaction;

import java.util.Date;

public class JsonTransactionModel {
    private Date date;
    private String fromAccount;
    private String toAccount;
    private String narrative;
    private Double amount;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public String getNarrative() {
        return narrative;
    }

    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Transaction toTransaction() {
        return new Transaction(
                date,
                fromAccount,
                toAccount,
                narrative,
                amount
        );
    }
}
