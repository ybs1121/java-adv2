package network.tcp.v6;

import network.tcp.SocketCloseUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.log;

public class SessionV6 implements Runnable {

    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final SessionManagerV6 sessionManager;
    private boolean closed = false;

    public SessionV6(Socket socket, SessionManagerV6 sessionManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.sessionManager = sessionManager;
        this.sessionManager.addSession(this);
    }

    @Override
    public void run() {
        try {

            while (true) {
                String received = input.readUTF();
                log("client -> server : " + received);

                if (received.equals("exit")) {
                    break;
                }

                String toSend = received + " World";
                output.writeUTF(toSend);
                log("client <- server : " + toSend);


            }

        } catch (IOException e) {
            log(e);
        } finally {
            sessionManager.removeSession(this);
            close();
        }


    }

    public synchronized void close() {
        if (closed) {
            return;
        }
        SocketCloseUtil.closeAll(socket, input, output);
        closed = true;
        log("연결 종료: " + socket + " isClosed: " + socket.isClosed());
    }
}
