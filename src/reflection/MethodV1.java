package reflection;

import reflection.data.BasicData;

import java.lang.reflect.Method;

public class MethodV1 {

    public static void main(String[] args) {
        Class<BasicData> basicDataClass = BasicData.class;

        System.out.println("===method()====");
        Method[] methods = basicDataClass.getMethods(); // public 만 보여준다

        for (Method method : methods) {
            System.out.println("Method: " + method);
        }

        System.out.println("===declaredMethods()====");
        Method[] declaredMethods = basicDataClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("declaredMethod " + declaredMethod); // 내가 선언한 메서드, 상속된 메서드는 포함하지 않는다.
        }


    }
}
