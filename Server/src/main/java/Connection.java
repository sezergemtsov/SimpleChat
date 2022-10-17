import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Connection implements Runnable {

    Thread t;
    Socket clientSocket;
    String clientAddress;
    String clientName;
    boolean isClientAddress;
    Server server;
    BufferedReader in;
    PrintWriter out;
    boolean isConnection;

    Connection(Server server) {
        try {
            this.server = server;
            clientSocket = server.serverSocket.accept();
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream());
            server.sender.newConnection(this);
            isConnection = true;
            isClientAddress = false;
            t = new Thread(this);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        server.logger.log("new connection");

        while (isConnection) {
            try {
                String massage = server.protocol.reConvert(in.readLine(),this);
                if (massage!=null) {
                    if (clientName != null) {
                        massage =  clientName + ": " + massage;
                    } else {
                        massage = clientAddress + ": " + massage;
                    }
                    server.logger.log(massage);
                    server.sender.massages.put(massage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setClientName(String name) {
        clientName = name;
    }

    public boolean isAddress() {
        return isClientAddress;
    }

    public void setClientAddress(String address) {
        this.clientAddress = address;
        isClientAddress = true;
    }

    protected void termination() {
        try {
            server.sender.terminateConnection(this);
            isConnection = false;
            in.close();
            out.close();
            clientSocket.close();
            t.interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public PrintWriter getWriter() {
        return out;
    }

}
