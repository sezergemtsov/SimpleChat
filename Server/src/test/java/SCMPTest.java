import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SCMPTest {

    Connection connection;
    private ServerSocket server;
    private Socket clientSocket;
    private String host = "127.0.0.1";
    private int port = 8080;

    @BeforeEach
    public void set() {
        try {
            server = new ServerSocket(port);
            clientSocket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        connection = Mockito.mock(Connection.class);
    }

    @AfterEach
    public void clean() {
        try {
            clientSocket.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void convertTest() {
        //arrange
        String expectedResult = "";
        SCMP entity = new SCMP();
        String result;
        //act
        result = entity.reConvert("/127.0.0.1//name//null//text//test", connection);
        //assert
        Assertions.assertEquals("test", result);
    }

    @Test
    public void sendTextTest() {
        //arrange
        String expectedResult = "/127.0.0.1//name//null//text//test";
        //act
        String result = SCMP.sendText("test", clientSocket, null);
        //assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void sendNameTest() {
        //arrange
        String expectedResult = "/127.0.0.1//name//null//name//test";
        //act
        String result = SCMP.sendName("test", clientSocket, null);
        //assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void sendExitTest() {
        //arrange
        String expectedResult = "/127.0.0.1//name//null//name//test";
        //act
        String result = SCMP.sendName("test", clientSocket, null);
        //assert
        Assertions.assertEquals(expectedResult, result);
    }


}
