package was.v6;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;

public class SiteControllerV6 {

    public void site1 (HttpRequest request, HttpResponse response) {
        response.writeBody("HTTP/1.1 200 OK");
    }

    public void site2 (HttpRequest request, HttpResponse response) {
        response.writeBody("<h1> site2 </h1>");
    }
}
