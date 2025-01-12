
package was.v3;

import was.v2.HttpRequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static util.MyLogger.log;

public class HttpServerV3 {

    private final int port;
    private final ExecutorService es = Executors.newFixedThreadPool(10);

    public HttpServerV3(int port) {
        this.port = port;
    }

    public void start() throws IOException {

        ServerSocket serverSocket = new ServerSocket(port);
        log("서버 시작 port : " + port);
        while (true) {
            Socket socket = serverSocket.accept();
            HttpRequestHandlerV3 httpRequestHandler = new HttpRequestHandlerV3(socket);
            es.submit(httpRequestHandler);
        }

    }
}
