import java.net.Socket;

public class SCMP {

    //Simple Chat Massage Protocol "SCMP"

    SCMP() {}

    public String reConvert (String text, Connection connection) {

        String[] splitText = text.split("//");
        String respond = null;

        if (!connection.isClientAddress) {
            connection.setClientAddress(splitText[0]);
        }

        if (splitText[3].equals("name")) {
            connection.setClientName(splitText[4]);
            String log = splitText[0] + " set new name: " + splitText[4];
            connection.server.logger.log(log);
            return null;
        }

        if (splitText[3].equals("exit")) {
            String log = splitText[0] + " quit from chat";
            connection.server.logger.log(log);
            connection.termination();
            return null;
        }

        if (splitText[3].equals("text")) {
            respond = splitText[4];
        }

        return respond;
    }

    public static String sendText(String massage, Socket clientSocket, String name) {

        StringBuilder stB = new StringBuilder();

        stB.append(clientSocket.getLocalAddress());
        stB.append("//");
        stB.append("name//");
        if (name != null) {
            stB.append(name);
        } else {
            stB.append("null");
        }
        stB.append("//");
        stB.append("text//");
        stB.append(massage);

        return stB.toString();
    }

    public static String sendName(String massage, Socket clientSocket, String name) {

        StringBuilder stB = new StringBuilder();

        stB.append(clientSocket.getLocalAddress());
        stB.append("//");
        stB.append("name//");
        if (name != null) {
            stB.append(name);
        } else {
            stB.append("null");
        }
        stB.append("//");
        stB.append("name//");
        stB.append(massage);

        return stB.toString();
    }

    public static String sendExit(String massage, Socket clientSocket, String name) {

        StringBuilder stB = new StringBuilder();

        stB.append(clientSocket.getLocalAddress().toString());
        stB.append("//");
        stB.append("name//");
        if (name != null) {
            stB.append(name);
        } else {
            stB.append("null");
        }
        stB.append("//");
        stB.append("exit//");
        stB.append(massage);

        return stB.toString();
    }


}
