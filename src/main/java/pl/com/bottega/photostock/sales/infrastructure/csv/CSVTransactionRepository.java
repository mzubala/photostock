package pl.com.bottega.photostock.sales.infrastructure.csv;

import com.sun.deploy.util.StringUtils;
import pl.com.bottega.photostock.sales.model.client.Transaction;

import java.io.File;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;

class CSVTransactionRepository {

    private String folderPath;

    public CSVTransactionRepository(String folderPath) {
        this.folderPath = folderPath;
    }

    public void saveTransactions(String clientNumber, Collection<Transaction> transactions) {
        try (PrintWriter pw = new PrintWriter(getRepositoryPath(clientNumber))) {
            for (Transaction t : transactions) {
                String[] components = {
                        t.getValue().toString(),
                        t.getDescription(),
                        t.getTimestamp().format(DateTimeFormatter.ISO_DATE_TIME)
                };
                pw.println(StringUtils.join(Arrays.asList(components), ","));
            }
        } catch (Exception ex) {
            throw new DataAccessException(ex);
        }
    }

    public Collection<Transaction> getTransactions(String clientNumber) {
        return null;
    }

    private String getRepositoryPath(String clientNumber) {
        return folderPath + File.separator + "clients-" + clientNumber + "-transactions.csv";
    }

}
