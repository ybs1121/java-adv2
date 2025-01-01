package network.tcp.v6;

import network.tcp.v5.SessionV5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class ServerV6 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("서버 시작");
        SessionManagerV6 sessionManagerV6 = new SessionManagerV6();
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("서버 소켓 시작 - 리스닝 포트 : + " + PORT);

        // ShutdownHook 등록
        ShutdownHook shutdownHook = new ShutdownHook(serverSocket, sessionManagerV6);
        Runtime.getRuntime().addShutdownHook(new Thread(shutdownHook, "shutdown-hook"));


        try {
            while (true) {
                Socket socket = serverSocket.accept(); // 블로킹
                log("소캣 연결 : " + socket);
                SessionV6 session = new SessionV6(socket, sessionManagerV6);
                Thread thread = new Thread(session);
                thread.start();
            }
        } catch (IOException e) {
            log("서버 소켓 종료 : " + e);

        }


    }

    static class ShutdownHook implements Runnable {
        private final ServerSocket serverSocket;
        private final SessionManagerV6 sessionManagerV6;

        public ShutdownHook(ServerSocket serverSocket, SessionManagerV6 sessionManagerV6) {
            this.serverSocket = serverSocket;
            this.sessionManagerV6 = sessionManagerV6;
        }

        @Override
        public void run() {
            log("shutdown hook");
            try {
                sessionManagerV6.closeAll();
                serverSocket.close();

                Thread.sleep(1000); // 자원 정리 대기
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("e = " + e);
            }
        }


    }

}
