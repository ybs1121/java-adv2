package annotation.basic;

import java.lang.reflect.Method;

@AnnoMeta
public class MetaData {

//    @AnnoMeta -> 컴파일 오류
    private String id;


    @AnnoMeta
    public void call() {

    }

    public static void main(String[] args) throws NoSuchMethodException {
        AnnoMeta annotation = MetaData.class.getAnnotation(AnnoMeta.class);
        System.out.println("annotation = " + annotation);

        Method call = MetaData.class.getMethod("call");
        AnnoMeta annotation1 = call.getAnnotation(AnnoMeta.class);
        System.out.println("annotation1 = " + annotation1);
    }
}
