package training.supportbank.input.xml;

import javax.xml.bind.annotation.XmlElement;

public class XmlPartiesModel {
    private String from;
    private String to;

    public String getFrom() {
        return from;
    }

    @XmlElement(name = "From")
    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    @XmlElement(name = "To")
    public void setTo(String to) {
        this.to = to;
    }
}
