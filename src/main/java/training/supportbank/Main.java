package training.supportbank;

import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String args[]) throws IOException, ParseException {
        TransactionParser parser = new TransactionParser();

        parser.parseFile();
    }
}
