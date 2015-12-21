package task_1;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import task_1.servlets.SimplyMirrorServlet;

/**
 * Created by whoosh on 12/18/15.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        SimplyMirrorServlet sm = new SimplyMirrorServlet();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(sm), "/mirror");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        System.out.println("Server started");
        server.join();
    }
}
