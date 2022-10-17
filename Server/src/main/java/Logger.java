import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {

    private static Logger instance;

    public void log(String message) {

        String log = "[ "+ LocalDateTime.now() + " ]" + message + "\n";
        byte[] bytes = log.getBytes();
        System.out.println(message);

        String filePath = "Server/src/main/resources/ChatLog.txt";
        File file = new File(filePath);
        try(FileOutputStream ous = new FileOutputStream(file, true)) {
            file.createNewFile();
            ous.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    Logger() {}

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
}
