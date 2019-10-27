package training.supportbank.input;

import java.util.List;

public interface TransactionParser {
    boolean canHandleFileType(String fileType);
    List<TransactionModel> parseFile(String filename) throws Exception;
}
