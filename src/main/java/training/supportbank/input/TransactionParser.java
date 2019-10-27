package training.supportbank.input;

import training.supportbank.Transaction;

import java.util.List;

public interface TransactionParser {
    boolean canHandleFileType(String fileType);
    List<Transaction> parseFile(String filename) throws Exception;
}
