package training.supportbank.input;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import training.supportbank.Transaction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JsonTransactionParser implements TransactionParser {
    private static final Logger LOGGER = LogManager.getLogger();

    private final Gson gson;

    public JsonTransactionParser() {
        gson = new GsonBuilder().create();
    }

    @Override
    public boolean canHandleFileType(String fileType) {
        return fileType.equals("json");
    }

    @Override
    public List<Transaction> parseFile(String filename) throws Exception {
        String jsonContent = getFileContent(filename);
        JsonTransactionModel[] models = gson.fromJson(jsonContent, JsonTransactionModel[].class);
        return Arrays.stream(models)
                .map(JsonTransactionModel::toTransaction)
                .collect(Collectors.toList());
    }

    private String getFileContent(String filename) throws IOException {
        Path path = Path.of(filename);
        try {
            return Files.readString(path);
        } catch (IOException e) {
            LOGGER.error(String.format("File '%s' does not exist", filename));
            System.out.printf("\033[31mUnable to find file: %s\n\033[0m", filename);
            throw e;
        }
    }
}
