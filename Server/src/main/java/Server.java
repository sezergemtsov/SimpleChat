import java.io.IOException;
import java.net.ServerSocket;

public class Server implements Runnable {

    Thread t;
    ServerSocket serverSocket;
    Sender sender;
    Logger logger;
    SCMP protocol;

    Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            logger = new Logger();
            Logger.getInstance();
            sender = new Sender();
            protocol = new SCMP();
            t = new Thread(this);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        logger.log("Server started");

        while (true) {
            try {
                new Connection(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
