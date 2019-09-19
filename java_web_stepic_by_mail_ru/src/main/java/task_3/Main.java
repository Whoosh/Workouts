package task_3;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import task_3.service.AccountService;
import task_3.servlets.EchoServlet;
import task_3.servlets.SessionsServlet;
import task_3.servlets.SignInServlet;
import task_3.servlets.SignUpServlet;
import tazk_3.profiles.UserProfile;

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
