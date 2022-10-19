public class Main {
    public static void main(String[] args) {

        String host;
        int port;

        SettingsHelper helper = new SettingsHelper();
        helper.read();

        host = helper.getHost();
        port = helper.getPort();

        new Server(port);

    }
}
