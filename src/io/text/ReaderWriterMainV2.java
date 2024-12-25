package io.text;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ReaderWriterMainV2 {
    public static void main(String[] args) throws IOException {
        String writeString = "ABCD";
        System.out.println("writeString: " + writeString);

        // 파일 쓰기
        FileOutputStream fos = new FileOutputStream(TextConst.FILE_NAME);
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        osw.write(writeString);
        osw.close();

        //일기
        FileInputStream fis = new FileInputStream(TextConst.FILE_NAME);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);

        StringBuffer sb = new StringBuffer();
        int ch;
        while ((ch = isr.read()) != -1) {
            sb.append((char) ch);
        }

        isr.close();

        System.out.println("read String " + sb);
    }
}
