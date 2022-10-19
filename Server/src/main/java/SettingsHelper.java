import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SettingsHelper {

    String fileName = "Server/src/main/resources/Settings.txt";
    String host;
    int port;

    SettingsHelper() {

    }

    SettingsHelper(String fileName) {
        this.fileName = fileName;
    }

    public void read() {
        try {
            FileInputStream fis = new FileInputStream(fileName);
            String str = new String(fis.readAllBytes());
            fis.close();
            String[] str1 = str.split("\n");
            host = str1[0].split(" ")[1].trim();
            port = Integer.parseInt(str1[1].split(" ")[1].trim());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
