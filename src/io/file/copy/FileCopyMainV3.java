package io.file.copy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileCopyMainV3 {
    public static void main(String[] args) throws IOException {
        String oldFileName = "temp/copy.dat";
        String newFileName = "temp/new_copy.dat";

        long startTime = System.currentTimeMillis();
        Path source = Path.of(oldFileName);
        Path target = Path.of(newFileName);

        Files.copy(source,target, StandardCopyOption.REPLACE_EXISTING);

        long endTime = System.currentTimeMillis();

        System.out.println("newFileName created : " + newFileName);
//        System.out.println("File Size: " + File_SIZE / 1024 / 1024 + " MB");
        System.out.println("File taken : " + (endTime - startTime) + "ms");
    }
}
