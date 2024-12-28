package io.file.text;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class RealTextFIleV2 {

    private static final String PATH = "temp/hello2.txt";


    public static void main(String[] args) throws IOException {
        String writeString = "abc \n 가나다";
        System.out.println("== Write String == ");
        System.out.println(writeString);

        Path path = Path.of(PATH);

        // 파일에 쓰기

        Path path1 = Files.writeString(path, writeString, StandardCharsets.UTF_8);
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

        System.out.println("== Read String == ");
        for (String line : lines) {
            System.out.println(line);
        }
        Stream<String> lineStream = Files.lines(path, StandardCharsets.UTF_8);
        lineStream.forEach(System.out::println);
        lineStream.close();
    }
}
