import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Writer implements Runnable {

    Thread t;
    Socket clientSocket;
    Scanner scanner;
    boolean isConnected = true;

    Writer(Socket clientSocket) {
        t = new Thread(this);
        this.clientSocket = clientSocket;
        scanner = new Scanner(System.in);
        t.start();
    }

    @Override
    public void run() {
        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            System.out.println("Please enter your name, if you want to disconnect from chat enter /exit");

            String name = scanner.nextLine();
            switch (name) {
                case "/exit":
                    out.println(SCMP.sendExit(name, clientSocket, Main.name));
                    System.out.println("End of session");
                    isConnected = false;
                    break;
                default:
                    out.println(SCMP.sendName(name, clientSocket, Main.name));
                    Main.setName(name);
                    break;
            }

            while (isConnected) {
                String massage = scanner.nextLine();
                switch (massage) {
                    case "/exit":
                        out.println(SCMP.sendExit(massage, clientSocket, Main.name));
                        System.out.println("End of session");
                        isConnected = false;
                        break;
                    default:
                        out.println(SCMP.sendText(massage, clientSocket, Main.name));
                        break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
