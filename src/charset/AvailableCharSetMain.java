package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.SortedMap;

public class AvailableCharSetMain {

    public static void main(String[] args) {
        // 이용가능한 Charset
        SortedMap<String, Charset> stringCharsetSortedMap = Charset.availableCharsets();
        for (String s : stringCharsetSortedMap.keySet()) {
            System.out.println("charset : " + s);
        }
        System.out.println("====");
        Charset ms949 = Charset.forName("MS949");
        System.out.println("ms949 : " + ms949);
        Set<String> aliases = ms949.aliases();
        for (String alias : aliases) {
            System.out.println(alias);
        }

        Charset charset = Charset.forName("utf-8");
        System.out.println(charset);
        Charset utf8 = StandardCharsets.UTF_8;
        System.out.println(utf8);

        // 시스템 기본 charset
        Charset charset1 = Charset.defaultCharset();
        System.out.println(charset1);
    }
}
