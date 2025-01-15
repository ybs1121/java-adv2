package reflection;

import reflection.data.BasicData;

import java.lang.reflect.Field;

public class FieldV1 {
    public static void main(String[] args) {
        Class<BasicData> basicDataClass = BasicData.class;

        System.out.println("====fields()====");
        Field[] fields = basicDataClass.getFields(); // 상속  + public

        for (Field field : fields) {
            System.out.println("field: " + field);
        }
        System.out.println("====declaredFields()====");
        Field[] declaredFields = basicDataClass.getDeclaredFields(); // 해당 클래스에 선언된 모든 클래스
        for (Field declaredField : declaredFields) {
            System.out.println("declaredField: " + declaredField);
        }





    }
}
