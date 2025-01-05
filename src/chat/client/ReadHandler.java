package chat.client;

import java.io.DataInputStream;
import java.io.IOException;

import static util.MyLogger.log;

public class ReadHandler implements Runnable {

    private final DataInputStream input;
    private final Client client;
    public boolean isClosed = false;

    public ReadHandler(DataInputStream input, Client client) {
        this.client = client;
        this.input = input;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String received = null;
                received = input.readUTF();
                System.out.println(received);
            }

        } catch (IOException e) {
            log(e);

        } finally {
            client.close();
        }

    }

    public synchronized void close() {
        if (isClosed) {
            return;
        }
        // 종료 로직 필요시 작성
        isClosed = true;
        log("readHandler closed");
    }
}
