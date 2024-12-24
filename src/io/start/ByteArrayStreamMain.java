package io.start;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayStreamMain {
    public static void main(String[] args) throws IOException {
        byte[] input = {1, 2, 3};

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos.write(input);

        // 메모리에서 읽기
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        byte[] readAllBytes = bis.readAllBytes();
        System.out.println(Arrays.toString(readAllBytes));

        bos.close();
        bis.close();
    }
}
