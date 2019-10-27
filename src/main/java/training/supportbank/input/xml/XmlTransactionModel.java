package training.supportbank.input.xml;

import training.supportbank.Transaction;
import training.supportbank.input.TransactionModel;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

public class XmlTransactionModel implements TransactionModel {
    private Date date;
    private String description;
    private XmlPartiesModel parties;
    private Double value;

    @XmlAttribute(name = "Date")
    public void setDate(Date date) {
        this.date = date;
    }

    @XmlElement(name = "Description")
    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(name = "Parties")
    public void setParties(XmlPartiesModel parties) {
        this.parties = parties;
    }

    @XmlElement(name = "Value")
    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public Transaction toTransaction() {
        return new Transaction(date, parties.getFrom(), parties.getTo(), description, value);
    }
}
