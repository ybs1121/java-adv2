package io.file;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class newFilesMain {

    public static void main(String[] args) throws IOException {
        Path file = Path.of("temp/example.txt");
        Path directory = Path.of("temp/exampleDir");

        System.out.println("File exists: " + Files.exists(file));

        try {
            Files.createFile(file);
        } catch (FileAlreadyExistsException e) {
            System.out.println(file + "  file already exists");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Files.createDirectory(directory);
        } catch (FileAlreadyExistsException e) {
            System.out.println(directory + " directory already exists");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        Files.delete(file);
//        System.out.println("File deleted");

        System.out.println("Is regular file: " + Files.isRegularFile(file));
        System.out.println("Is directory: " + Files.isDirectory(directory));
        System.out.println("File name: " + file.getFileName());
        System.out.println("File size: " + Files.size(file));

        Path newFile = Path.of("temp/newExample.txt");
        Files.move(file, newFile, StandardCopyOption.REPLACE_EXISTING);

        System.out.println("Last Modified: " + Files.getLastModifiedTime(file));

        BasicFileAttributes attrs = Files.readAttributes(file, BasicFileAttributes.class);
        System.out.println("====Attr=====");
        System.out.println("Created time: " + attrs.creationTime());
        System.out.println("Last Modified: " + attrs.lastModifiedTime());
        System.out.println("Size: " + attrs.size());
        System.out.println("Is regular file: " + attrs.isRegularFile());
        System.out.println("Is directory: " + attrs.isDirectory());
        System.out.println("is symbolic link: " + attrs.isSymbolicLink());


    }
}
