import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import task_2.controllers.ResourceServerController;
import task_2.controllers.ResourceServerControllerMBean;
import task_2.servlets.ResourcesServlet;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * Created by whoosh on 2/26/16.
 */

public class Main {
    public static void main(String[] args) throws Exception {

        ResourceServerControllerMBean resource = new ResourceServerController();
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("Admin:type=ResourceServerController");
        mbs.registerMBean(resource, name);

        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.addServlet(new ServletHolder(new ResourcesServlet(resource)), ResourcesServlet.HTTP_PAGE);
        server.setHandler(context);

        server.start();
        System.out.println("Server started");
        server.join();
    }
}
