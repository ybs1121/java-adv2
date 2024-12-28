package io.file.copy;

import java.io.FileOutputStream;
import java.io.IOException;

public class CreateCopyFile {

    private static final int File_SIZE = 1024 * 1024 * 200; //200MB

    public static void main(String[] args) throws IOException {
        String fileName = "temp/copy.dat";
        long startTime = System.currentTimeMillis();

        FileOutputStream fos = new FileOutputStream(fileName);
        byte[] buffer = new byte[File_SIZE];
        fos.write(buffer);
        fos.close();
        long endTime = System.currentTimeMillis();

        System.out.println("File created : " + fileName);
        System.out.println("File Size: " + File_SIZE / 1024 / 1024 + " MB");
        System.out.println("File taken : " + (endTime - startTime) + "ms");
    }
}
