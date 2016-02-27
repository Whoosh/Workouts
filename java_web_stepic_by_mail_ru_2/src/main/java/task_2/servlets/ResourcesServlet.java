package task_2.servlets;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import task_2.controllers.ResourceServerControllerMBean;
import task_2.resources.TestResource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 * Created by whoosh on 2/26/16.
 */
public class ResourcesServlet extends HttpServlet {

    public final static String HTTP_PAGE = "/resources";

    private ResourceServerControllerMBean resource;

    public ResourcesServlet(ResourceServerControllerMBean resource) {
        this.resource = resource;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FileReader fileReader = new FileReader(new File(req.getParameter("path")));
        BufferedReader reader = new BufferedReader(fileReader);
        Document doc = Jsoup.parse(reader.lines().reduce((x, y) -> x + y).get(), "", Parser.xmlParser());
        String name = doc.getElementsByTag("name").text();
        int age = Integer.parseInt(doc.getElementsByTag("age").text());
        resource.setResource(new TestResource(name, age));
        reader.close();
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
