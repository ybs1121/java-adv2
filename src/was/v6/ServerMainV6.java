package was.v6;

import was.httpserver.HttpServer;
import was.httpserver.ServletManager;
import was.httpserver.servlet.DiscardServlet;
import was.httpserver.servlet.reflection.ReflectionServlet;
import was.v5.servlet.HomeServlet;
import was.v5.servlet.SearchServlet;

import java.io.IOException;
import java.util.List;

public class ServerMainV6 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        List<Object> controller = List.of(new SiteControllerV6(), new SearchControllerV6());
        ReflectionServlet reflectionServlet = new ReflectionServlet(controller);

        ServletManager servletManager = new ServletManager();
        servletManager.setDefaultServlet(reflectionServlet);
        servletManager.add("/", new HomeServlet());
        servletManager.add("/favicon.ico", new DiscardServlet());
        HttpServer server = new HttpServer(PORT, servletManager);
        server.start();
    }
}
