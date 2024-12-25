package io.text;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ReaderWriterMainV1 {
    public static void main(String[] args) throws IOException {
        String writeString = "ABC";

        byte[] writeBytes = writeString.getBytes(StandardCharsets.UTF_8);
        System.out.println("writeString: " + writeString);
        System.out.println("writeBytes : " + Arrays.toString(writeBytes));

        FileOutputStream fos = new FileOutputStream(TextConst.FILE_NAME);
        fos.write(writeBytes);
        fos.close();

        // 파일 읽기

        FileInputStream fis = new FileInputStream(TextConst.FILE_NAME);
        byte[] readAllBytes = fis.readAllBytes();
        System.out.println("readAllBytes : " + Arrays.toString(readAllBytes));
        System.out.println("readAllBytes : " + new String(readAllBytes, StandardCharsets.UTF_8));
        fis.close();
    }
}
