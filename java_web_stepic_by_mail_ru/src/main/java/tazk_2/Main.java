package tazk_2;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import tazk_2.profiles.UserProfile;
import tazk_2.service.AccountService;
import tazk_2.servlets.EchoServlet;
import tazk_2.servlets.SessionsServlet;
import tazk_2.servlets.SignInServlet;
import tazk_2.servlets.SignUpServlet;

/**
 * Created by whoosh on 12/21/15.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        startServer().join();
    }

    public static Server startServer() throws Exception {
        AccountService accountService = new AccountService();
        accountService.addNewUser(new UserProfile("test", "test"));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new EchoServlet()),"/echo");
        context.addServlet(new ServletHolder(new SessionsServlet(accountService)), "/api/v1/sessions");
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
        context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        System.out.println("Server started");
        return server;
    }
}
