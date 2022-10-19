import java.io.IOException;
import java.net.Socket;

public class Main {

    static boolean isConnected = true;

    static String name;

    public static void main(String[] args) {

        SettingsHelper helper = new SettingsHelper("Client_Test1/src/main/resources/Settings.txt");
        helper.read();
        String host = helper.getHost();
        int port = helper.getPort();

        System.out.println(host);

        Socket clientSocket;
        Listener listener;
        Writer writer;

        try {
            clientSocket = new Socket(host, port);
            listener = new Listener(clientSocket);
            writer = new Writer(clientSocket);

            System.out.println("Connection attempt was accepted");

            try {
                listener.t.join();
                writer.t.join();
                clientSocket.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void changeStatus() {
        isConnected = false;
    }

    static void setName(String name) {
        Main.name = name;
    }

}
