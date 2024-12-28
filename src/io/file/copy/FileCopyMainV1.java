package io.file.copy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyMainV1 {


    public static void main(String[] args) throws IOException {
        String oldFileName = "temp/copy.dat";
        String newFileName = "temp/new_copy.dat";

        long startTime = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream(oldFileName);
        FileOutputStream fos = new FileOutputStream(newFileName);

        byte[] bytes = fis.readAllBytes();
        fos.write(bytes);
        fos.close();
        fis.close();

        long endTime = System.currentTimeMillis();

        System.out.println("newFileName created : " + newFileName);
//        System.out.println("File Size: " + File_SIZE / 1024 / 1024 + " MB");
        System.out.println("File taken : " + (endTime - startTime) + "ms");
    }
}
