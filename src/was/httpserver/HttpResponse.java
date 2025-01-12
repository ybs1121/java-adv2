package was.httpserver;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class HttpResponse {

    private final PrintWriter writer;
    private int statusCode = 200;
    private final StringBuilder builder = new StringBuilder();
    private String contentType = "text/html charset=utf-8";

    public HttpResponse(PrintWriter writer) {
        this.writer = writer;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void writeBody(String body) {
        builder.append(body);
    }

    public void flush() {
        int contentLength = builder.toString().getBytes(StandardCharsets.UTF_8).length;
        writer.println("HTTP/1.1 " + statusCode + " " + getReasonPhrase(statusCode));
        writer.println("Content-Type: " + contentType);
        writer.println("Content-Length: " + contentLength);
        writer.println();
        writer.println(builder);
        writer.flush();
    }

    public String getReasonPhrase(int statusCode) {
        switch (statusCode) {
            case 200:
                return "OK";
            case 404:
                return "Not Found";
            case 500:
                return "Internal Server Error";
            default:
                return "Unknown";
        }
    }
}
