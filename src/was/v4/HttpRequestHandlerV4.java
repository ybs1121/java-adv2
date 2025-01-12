package was.v4;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import static util.MyLogger.log;

public class HttpRequestHandlerV4 implements Runnable {

    private final Socket socket;

    public HttpRequestHandlerV4(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            process();
        } catch (Exception e) {
            log(e);
        }
    }

    private void process() {
        try (socket;
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), false, StandardCharsets.UTF_8);
        ) {

            HttpRequest httpRequest = new HttpRequest(in);
            HttpResponse httpResponse = new HttpResponse(out);

            if (httpRequest.getPath().equals("/favicon.ico")) {
                log("파비콘 요청");
                return;
            }


            log("HTTP 요청 정보 출력");
            if (httpRequest.getPath().equals("/site1")) {
                site1(httpResponse);
            } else if (httpRequest.getPath().equals("/site2")) {
                site2(httpResponse);
            } else if (httpRequest.getPath().equals("/search")) {
                search(httpRequest, httpResponse);
            } else if (httpRequest.getPath().equals("/")) {
                home(httpResponse);
            } else {
                notFound(httpResponse);
            }


            httpResponse.flush();

            log("HTTP 응답 전달 완료");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void home(HttpResponse response) {

        response.writeBody("<h1> home </h1>");
        response.writeBody("<ul>");
        response.writeBody("<li><a href='/site1'> site1 </a></li>");
        response.writeBody("<li><a href='/site2'> site2 </a></li>");
        response.writeBody("<li><a href='/search?q=hello'> search </a></li>");
        response.writeBody("</ul>");

    }

    private void notFound(HttpResponse response) {
        response.setStatusCode(404);
        response.writeBody("<h1> 404 페이지를 찾을 수 없습니다.  </h1>");

    }


    private void search(HttpRequest request, HttpResponse response) {
        String query = request.getParameter("q");

        response.writeBody("<meta charset=\"utf-8\">");
        response.writeBody("<h1> Search </h1>");
        response.writeBody("<ul>");
        response.writeBody("<li>query : " + query + "</li>");
        response.writeBody("</ul>");
    }

    private void site2(HttpResponse response) {
        response.writeBody("<h1> site2 </h1>");
    }

    private void site1(HttpResponse response) {
        response.writeBody("HTTP/1.1 200 OK");
    }


    private void responseToClient(PrintWriter writer) {
        // 웹 브라우저에게 전달하는 내용

        String body = "<h1>Hello World!</h1>";
        int length = body.getBytes(StandardCharsets.UTF_8).length;
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Content-Type: text/html\r\n");
        sb.append("Content-Length: " + length + "\r\n");
        sb.append("\r\n"); // header, body 구분
        sb.append(body);

        log("HTTP 응답 정보 출력");
        System.out.println(sb);

        writer.println(sb);
        writer.flush();
    }

    private static String requestToString(BufferedReader in) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }
            sb.append(line).append("\n");
        }

        return sb.toString();
    }
}
