package tazk_2;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import tazk_2.service.AccountService;
import tazk_2.servlets.EchoServlet;
import tazk_2.servlets.SessionsServlet;
import tazk_2.servlets.UsersServlet;

/**
 * Created by whoosh on 12/21/15.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        startServer().join();
    }

    public static Server startServer() throws Exception {
        AccountService accountService = new AccountService();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new UsersServlet(accountService)), "/api/v1/users");
        context.addServlet(new ServletHolder(new SessionsServlet(accountService)), "/api/v1/sessions");
        context.addServlet(new ServletHolder(new EchoServlet()), "/echo");

        ResourceHandler resource_handler = new ResourceHandler();

        resource_handler.setResourceBase("src/main/resources/public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        System.out.println("Server started");
        return server;
    }
}
