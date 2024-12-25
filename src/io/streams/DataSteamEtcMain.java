package io.streams;

import java.io.*;

public class DataSteamEtcMain {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp/data.dat");
        DataOutputStream dos = new DataOutputStream(fos);
        dos.writeUTF("회원A");
        dos.writeInt(10);
        dos.writeDouble(10.5);

        FileInputStream fis = new FileInputStream("temp/data.dat");
        DataInputStream dis = new DataInputStream(fis);
        System.out.println(dis.readUTF());
        System.out.println(dis.readInt());
        System.out.println(dis.readDouble());
    }
}
