import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {
    private String fileName;
    private File file;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public void init() throws IOException {
        this.file = new File("C://Users//Trinita//Documents", this.fileName);
        if (!this.file.canWrite())
            throw new IOException("Block File!!!");
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(this.file, event.toString() + "\r\n", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
