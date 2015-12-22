package task_2;

import org.eclipse.jetty.server.Server;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tazk_2.Main;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
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
    private static final String ECHO_URL = "http://localhost:8080/echo";
    private static final String REGISTRATION_URL = "http://localhost:8080/signup";
    private static final String LOG_IN_URL = "http://localhost:8080/signin";
    private static final String LOGIN_PARAM = "login=";
    private static final String PASSWORD_PARAM = "password=";
    private static final String LOGIN = "man";
    private static final String PASSWORD = "mans password";

    private Server server;

    @Before
    public void runServer() throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Server> submit = executorService.submit(() -> {
            try {
                return Main.startServer();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
        server = submit.get();
    }

    @After
    public void stopServer() throws Exception {
        server.stop();
    }

    @Test
    public void isServerAliveTest() throws IOException {
        String message = "hello";
        URL obj = new URL(ECHO_URL + "?param=" + message);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        Assert.assertEquals(HttpServletResponse.SC_OK, con.getResponseCode());
        Scanner scanner = new Scanner(con.getInputStream());
        Assert.assertEquals(message, scanner.nextLine());
    }

    @Test
    public void tryToRegisterTest() throws Exception {
        URL obj = new URL(REGISTRATION_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(LOGIN_PARAM.getBytes());
        os.write(LOGIN.getBytes());
        os.write(',');
        os.write(PASSWORD_PARAM.getBytes());
        os.write(PASSWORD.getBytes());
        os.flush();
        os.close();
        Assert.assertEquals(HttpServletResponse.SC_OK, con.getResponseCode());
        Scanner scanner = new Scanner(con.getInputStream());
        System.out.println(scanner.nextLine());
    }
}