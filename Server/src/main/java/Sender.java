import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Sender implements Runnable {

    Thread t;
    ArrayList<Connection> connections;
    BlockingQueue<String> massages;

    Sender() {
        t = new Thread(this);
        connections = new ArrayList<>();
        massages = new ArrayBlockingQueue<>(100);
        t.start();
    }

    @Override
    public void run() {
        while (t.isAlive()) {
            try {
                String massage = massages.take();
                sendEveryone(massage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void newConnection(Connection connection) {
        synchronized (connection) {
            connections.add(connection);
        }
    }

    public void terminateConnection(Connection connection) {
        synchronized (connection) {
            connections.remove(connection);
        }
    }

    public void sendEveryone(String massage) {
        connections.forEach(x -> {
            PrintWriter writer = x.getWriter();
            writer.println(massage);
            writer.flush();
        });
    }

}
