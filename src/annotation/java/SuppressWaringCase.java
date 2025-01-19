package annotation.java;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SuppressWaringCase {

    @SuppressWarnings("unused")
    public void multipleWaring() {
        // 사용되지 않는 변수 경고 억제

        int unusedVar = 10;
    }

    @SuppressWarnings("deprecation")
    public void deprecatedMethod() {
        Date date = new Date();
        int date1 = date.getDate();
    }


    @SuppressWarnings({"rawtypes", "unchecked"})
    public void uncheckedCast() {
        List arrayList = new ArrayList();
        List<String> strings = (List<String>) arrayList;
    }

    @SuppressWarnings("all")
    public void suppressAllWarning() {
        Date date = new Date();
        int date1 = date.getDate();
        List arrayList = new ArrayList();
        List<String> strings = (List<String>) arrayList;
    }
}
