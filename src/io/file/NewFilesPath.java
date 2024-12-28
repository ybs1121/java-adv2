package io.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class NewFilesPath {

    public static void main(String[] args) throws IOException {
        Path path = Path.of("temp/..");
        System.out.println("path = " + path);


        System.out.println("Absolute path = " + path.toAbsolutePath());

        // 정규경로
        System.out.println("Canonical path = " + path.toRealPath());

        Stream<Path> fileStream1 = Files.list(path);
        List<Path> paths = fileStream1.toList();
        fileStream1.close();
        for (Path p : paths) {
            System.out.println((Files.isRegularFile(p) ? "F" : "D") + " " + p.getFileName());
        }

        Stream<Path> fileStream2 = Files.list(path);
        fileStream2.forEach(p -> System.out.println((Files.isRegularFile(p) ? "F" : "D") + " " + p.getFileName()));
        fileStream2.close();
    }
}
