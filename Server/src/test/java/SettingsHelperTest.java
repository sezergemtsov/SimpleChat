import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SettingsHelperTest {
    @Test
    void readTest() {

        //arrange
        String fileName = "src/test/java/SettingsTest.txt";
        String expectedHost = "testHost";
        int expectedPort = 3131;

        //act
        SettingsHelper helper = new SettingsHelper(fileName);
        helper.read();
        String host = helper.getHost();
        int port = helper.getPort();

        //assert
        assertThat(host, equalTo(expectedHost));
        assertThat(port, equalTo(expectedPort));
    }
}
