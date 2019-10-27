package training.supportbank.input.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import training.supportbank.input.TransactionModel;
import training.supportbank.input.TransactionParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class XmlTransactionParser implements TransactionParser {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public boolean canHandleFileType(String fileType) {
        return fileType.equals("xml");
    }

    @Override
    public List<TransactionModel> parseFile(String filename) throws Exception {
        try {
            File file = new File(filename);
            XmlTransactionListModel transactionListModel = (XmlTransactionListModel) getUnmarshaller().unmarshal(file);
            return transactionListModel.getTransactions().stream()
                    .map(t -> (TransactionModel) t)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Failed to unmarshal XML", e);
            throw e;
        }
    }

    private Unmarshaller getUnmarshaller() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(XmlTransactionListModel.class);
        return context.createUnmarshaller();
    }
}
