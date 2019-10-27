package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import training.supportbank.input.InputUserInterface;
import training.supportbank.output.OutputUserInterface;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String args[]) throws IOException, ParseException {
        LOGGER.info("Beginning Program.");
        List<Transaction> transactions = runInput();

        LOGGER.info("Transactions Parsed - Creating Bank.");
        Bank bank = new Bank(transactions);

        LOGGER.info("Running Output Program.");
        runOutput(bank);
        LOGGER.info("Successfully Ending Program");
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
