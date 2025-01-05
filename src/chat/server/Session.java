package chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static network.tcp.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

public class Session implements Runnable {

    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final CommandManager commandManager;
    private final SessionManager sessionManager;

    private boolean isClosed = false;
    private String username;

    public Session(CommandManager commandManager, Socket socket, SessionManager sessionManager) throws IOException {
        this.commandManager = commandManager;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.socket = socket;
        this.sessionManager = sessionManager;
        this.sessionManager.addSession(this);
    }

    @Override
    public void run() {
        try {
            while (true) {
                String received = input.readUTF();
                log("client -> server: " + received);

                commandManager.execute(received, this);
            }
        } catch (IOException e) {
            log(e);
        } finally {
            sessionManager.removeSession(this);
            sessionManager.sendAll(username + "님이 퇴장했습니다.");
        }
    }

    public void send(String message) throws IOException {
        log("client <- server: " + message);
        output.writeUTF(message);
    }

    public void close() {
        if (isClosed) return;

        closeAll(socket, input, output);
        isClosed = true;
        log("연결 종료 " + socket);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
