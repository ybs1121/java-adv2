package annotation.mapping;


import java.lang.reflect.Method;

public class TestControllerMain {

    public static void main(String[] args) {
        TestController testController = new TestController();

        Class<? extends TestController> aClass = testController.getClass();
        Method[] declaredMethods = aClass.getDeclaredMethods();

        for (Method declaredMethod : declaredMethods) {
            System.out.println("method: " + declaredMethod);
            SimpleMapping annotation = declaredMethod.getAnnotation(SimpleMapping.class);
            if (annotation != null) {
                System.out.println("[" + annotation.value() + "] ->" + declaredMethod);
            }
        }
    }
}
