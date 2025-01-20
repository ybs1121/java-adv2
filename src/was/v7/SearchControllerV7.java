package was.v7;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.servlet.annotation.Mapping;

import java.io.IOException;

public class SearchControllerV7 {

    @Mapping("/search")
    public void search(HttpRequest request, HttpResponse response) throws IOException {
        String query = request.getParameter("q");

        response.writeBody("<meta charset=\"utf-8\">");
        response.writeBody("<h1> Search </h1>");
        response.writeBody("<ul>");
        response.writeBody("<li>query : " + query + "</li>");
        response.writeBody("</ul>");
    }
}
