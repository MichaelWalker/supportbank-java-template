package training.supportbank.input.xml;

import training.supportbank.input.TransactionModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "TransactionList")
public class XmlTransactionListModel {
    private List<XmlTransactionModel> transactions;

    public List<XmlTransactionModel> getTransactions() {
        return transactions;
    }

    @XmlElement(name = "SupportTransaction")
    public void setTransactions(List<XmlTransactionModel> transactions) {
        this.transactions = transactions;
    }
}
