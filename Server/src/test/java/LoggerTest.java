import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LoggerTest {

    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    public void set() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void clean() {
        System.setOut(null);
    }

    @Test
    public void testLog() {
        //arrange
        String testMassage = "test";
        //act
        Logger logger = Logger.getInstance();
        logger.log(testMassage);
        //assert
        Assertions.assertEquals("test\r\n", output.toString());
    }

}
