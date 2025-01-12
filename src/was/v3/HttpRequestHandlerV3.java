package was.v3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import static util.MyLogger.log;

public class HttpRequestHandlerV3 implements Runnable {

    private final Socket socket;

    public HttpRequestHandlerV3(Socket socket) {
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
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), false, StandardCharsets.UTF_8);
        ) {

            String requestString = requestToString(in);

            if (requestString.contains("/favicon.ico")) {
                log("favicon.ico req");
                return;
            }


            log("HTTP 요청 정보 출력");
            System.out.println(requestString);

            log("HTTP 요청 정보 출력");
            if (requestString.startsWith("GET /site1")) {
                site1(writer);
            } else if (requestString.startsWith("GET /site2")) {
                site2(writer);
            } else if (requestString.startsWith("GET /search")) {
                search(writer, requestString);
            } else if (requestString.startsWith("GET / ")) {
                home(writer, requestString);
            } else {
                notFound(writer);
            }

            log("HTTP 응답 전달 완료");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void home(PrintWriter writer, String requestString) {
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html charset=utf-8");
        writer.println();
        writer.println("<h1> home </h1>");
        writer.println("<ul>");
        writer.println("<li><a href='/site1'> site1 </a></li>");
        writer.println("<li><a href='/site2'> site2 </a></li>");
        writer.println("<li><a href='/search?q=hello'> search </a></li>");
        writer.println("</ul>");
        writer.flush();
    }

    private void notFound(PrintWriter writer) {
        writer.println("HTTP/1.1 404 Not Found");
        writer.println("Content-Type: text/html charset=utf-8");
        writer.println();
        writer.println("<h1> 404 페이지를 찾을 수 없습니다.  </h1>");
        writer.flush();
    }


    private void search(PrintWriter writer, String requestString) {
        int startIndex = requestString.indexOf("q=");
        int endIndex = requestString.indexOf(" ", startIndex + 2);
        String query = requestString.substring(startIndex + 2, endIndex);
        String decode = URLDecoder.decode(query, StandardCharsets.UTF_8);
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html charset=utf-8");
        writer.println();
        writer.println("<meta charset=\"utf-8\">");
        writer.println("<h1> Search </h1>");
        writer.println("<ul>");
        writer.println("<li>query : " + query + "</li>");
        writer.println("<li>decode : " + decode + "</li>");
        writer.println("</ul>");

        writer.flush();
    }

    private void site2(PrintWriter writer) {
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html charset=utf-8");
        writer.println();
        writer.println("<h1> site2 </h1>");
        writer.flush();
    }

    private void site1(PrintWriter writer) {
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html charset=utf-8");
        writer.println();
        writer.println("<h1> site1 </h1>");
        writer.flush();
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
