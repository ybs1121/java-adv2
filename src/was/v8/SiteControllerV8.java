package was.v8;

import was.httpserver.HttpResponse;
import was.httpserver.servlet.annotation.Mapping;

public class SiteControllerV8 {

    @Mapping("/")
    public void home(HttpResponse response) {
        response.writeBody("<h1> home </h1>");
        response.writeBody("<ul>");
        response.writeBody("<li><a href='/site1'> site1 </a></li>");
        response.writeBody("<li><a href='/site2'> site2 </a></li>");
        response.writeBody("<li><a href='/search?q=hello'> search </a></li>");
        response.writeBody("</ul>");
    }

    @Mapping("/site1")
    public void site1(HttpResponse response) {
        response.writeBody("HTTP/1.1 200 OK");
    }

    @Mapping("/site2")
    public void site2(HttpResponse response) {
        response.writeBody("<h1> site2 </h1>");
    }
}
