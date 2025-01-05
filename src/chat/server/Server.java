package chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class Server {

    private final int port;
    private final CommandManager commandManager;
    private final SessionManager sessionManager;

    private ServerSocket serverSocket;

    public Server(int port, CommandManager commandManager, SessionManager sessionManager) {
        this.port = port;
        this.commandManager = commandManager;
        this.sessionManager = sessionManager;
    }

    public void start() throws IOException {
        log("서버시작 " + commandManager.getClass());
        serverSocket = new ServerSocket(port);

        log("서버 소켓 시작 - 리스닝 포트 : " + port);

        addShutdownHook();
        running();


    }

    private void running() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                log("소캣 연결 " + socket);
                Session session = new Session(commandManager, socket, sessionManager);
                new Thread(session).start();
            }
        } catch (IOException e) {
            log("서버 소캣 종료: " + e.getMessage());
        }
    }

    private void addShutdownHook() {
        ShutdownHook target = new ShutdownHook(serverSocket, sessionManager);
        Runtime.getRuntime().addShutdownHook(new Thread(target, "shutdown hook"));
    }


    static class ShutdownHook implements Runnable {
        private final ServerSocket serverSocket;
        private final SessionManager sessionManager;

        public ShutdownHook(ServerSocket serverSocket, SessionManager sessionManager) {
            this.serverSocket = serverSocket;
            this.sessionManager = sessionManager;
        }

        @Override
        public void run() {
            log("shutdown hook");
            try {
                sessionManager.closeAll();
                serverSocket.close();

                Thread.sleep(1000); // 자원 정리 대기
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("e = " + e);
            }
        }
    }
}