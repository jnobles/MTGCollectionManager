package util;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class SQLiteDownloadWorker extends SwingWorker<Void, Integer> {
    private final String sourceURL;
    private final String targetPath;
    private long totalBytesRead;
    private long totalBytes;

    public SQLiteDownloadWorker() {
        sourceURL = "https://mtgjson.com/api/v5/AllPrintings.sqlite.gz";
        targetPath = "./AllPrintings.sqlite";
    }

    @Override
    protected Void doInBackground() throws Exception {
        try {
            URL url = new URL(sourceURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            try (InputStream inputStream = url.openStream();
                 BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                 GZIPInputStream gzipInputStream = new GZIPInputStream(bufferedInputStream);
                 FileOutputStream fileOutputStream = new FileOutputStream(targetPath)) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = gzipInputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                    totalBytesRead += bytesRead;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    protected void process(List<Integer> chunks) {
        if (isCancelled()) return;
        totalBytesRead = chunks.get(chunks.size() - 1);
    }
}
