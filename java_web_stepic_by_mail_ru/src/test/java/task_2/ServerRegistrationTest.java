package task_2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tazk_2.Main;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by whoosh on 12/21/15.
 */

public class ServerRegistrationTest {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String GET_URL = "http://localhost:8080/echo";
    private static final String ECHO_URL = "http://localhost:8080/";
    private static final String POST_URL = "http://localhost:8080/";
    private static final String POST_PARAMS = "a=b";
    private Thread testServerThread;
    // TODO
    @Before
    public void runServer() throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<?> submit = executorService.submit(() -> {
            try {
                Main.main(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        submit.get();
    }

    @Test
    public void isServerAliveTest() throws IOException {
        String message = "hello";
        URL obj = new URL(GET_URL + "?param=" + message);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        Assert.assertEquals(HttpServletResponse.SC_OK, con.getResponseCode());
        Scanner scanner = new Scanner(con.getInputStream());
        Assert.assertEquals("hello", scanner.nextLine());
    }
}