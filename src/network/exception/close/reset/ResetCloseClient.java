package network.exception.close.reset;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import static util.MyLogger.log;

public class ResetCloseClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 12345);
        OutputStream output = socket.getOutputStream();
        InputStream input = socket.getInputStream();
        log("소캣 연결 : " + socket);

        //client <- server FIN
        Thread.sleep(1000);

        output.write(1);


        //client <- server: RST
        Thread.sleep(1000);
        try {
            int read = input.read();
            System.out.println("read = " + read);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        try {
            int read = input.read();
            System.out.println("read = " + read);
        } catch (SocketException e) {
            e.printStackTrace();
        }


    }
}
