package training.supportbank;

import training.supportbank.output.OutputUserInterface;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class Main {
    public static void main(String args[]) throws IOException, ParseException {
        TransactionParser parser = new TransactionParser();
        List<Transaction> transactions = parser.parseFile();

        Bank bank = new Bank(transactions);

        OutputUserInterface outputUserInterface = new OutputUserInterface(bank);
        outputUserInterface.run();
    }
}
