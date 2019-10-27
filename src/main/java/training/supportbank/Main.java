package training.supportbank;

import training.supportbank.input.InputUserInterface;
import training.supportbank.output.OutputUserInterface;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class Main {
    public static void main(String args[]) throws IOException, ParseException {
        List<Transaction> transactions = runInput();

        Bank bank = new Bank(transactions);

        runOutput(bank);
    }

    private static List<Transaction> runInput() {
        InputUserInterface inputUserInterface = new InputUserInterface();
        return inputUserInterface.run();
    }

    private static void runOutput(Bank bank) {
        OutputUserInterface outputUserInterface = new OutputUserInterface(bank);
        outputUserInterface.run();
    }
}
