package network.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static util.MyLogger.log;

public class SocketCloseUtil {

    public static void closeAll(Socket socket, InputStream in, OutputStream out) {

        close(in);
        close(out);
        close(socket);
    }

    public static void close(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                log(e.getMessage());
            }
        }
    }

    public static void close(OutputStream out) {
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                log(e.getMessage());
            }
        }
    }

    public static void close(InputStream in) {
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                log(e.getMessage());
            }
        }
    }
}
