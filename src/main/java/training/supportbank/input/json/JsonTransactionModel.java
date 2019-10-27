package training.supportbank.input.json;

import training.supportbank.Transaction;
import training.supportbank.input.TransactionModel;

import java.util.Date;

public class JsonTransactionModel implements TransactionModel {
    private Date date;
    private String fromAccount;
    private String toAccount;
    private String narrative;
    private Double amount;

    public void setDate(Date date) {
        this.date = date;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
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
