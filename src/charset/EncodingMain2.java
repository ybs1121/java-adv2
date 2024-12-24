package charset;

import java.nio.charset.Charset;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.*;

public class EncodingMain2 {
    private static final Charset EUC_KR = Charset.forName("EUC-KR");
    private static final Charset MS_949 = Charset.forName("MS949");

    public static void main(String[] args) {
        System.out.println("== ASCII 영문처리 ==");
        test("A", US_ASCII, US_ASCII);
        test("A", US_ASCII, ISO_8859_1);
        test("A", US_ASCII, EUC_KR);
        test("A", US_ASCII, MS_949);
        test("A", US_ASCII, UTF_8);
        test("A", US_ASCII, UTF_16BE); // 디코딩 실패

        System.out.println("== 한글 ==");
        test("가", US_ASCII, US_ASCII);
        test("가", ISO_8859_1, ISO_8859_1);
        test("가", EUC_KR, EUC_KR);
        test("가", MS_949, MS_949);
        test("가", UTF_8, UTF_8);
        test("가", UTF_16BE, UTF_16BE);

        test("삒", EUC_KR, EUC_KR); // EUC_KR은 자주 쓰는 글자만 들고 있기 때문에 일부 없음
        test("삒", MS_949, MS_949);
        test("삒", UTF_8, UTF_8);
        test("삒", UTF_16BE, UTF_16BE);

        System.out.println("== 한글 인코딩 - 디코딩 다른경우==");
        test("가", EUC_KR, MS_949);
        test("삒", MS_949, EUC_KR); //인코딩은 가능 디코딩은 실패
        test("가", EUC_KR, UTF_8); // 실패
        test("가", MS_949, UTF_8); // 실패
        test("가", UTF_8, MS_949); // 실패


        System.out.println("== 영문 인코딩 - 디코딩 다른경우==");
        test("A", EUC_KR, UTF_8);
        test("A", MS_949, UTF_8);
        test("A", UTF_8, MS_949);
        test("A", UTF_8, UTF_16BE);
    }


    private static void test(String text, Charset encoding, Charset decoding) {
        byte[] encoded = text.getBytes(encoding);
        String decoded = new String(encoded, decoding);

        System.out.printf("%s -> [%s] 인코딩 -> %s %sbyte -> [%s] 디코딩 -> %s \n"
                , text, encoding, Arrays.toString(encoded), encoded.length, decoding, decoded);
    }

}
