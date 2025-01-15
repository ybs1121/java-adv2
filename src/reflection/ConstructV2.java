package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ConstructV2 {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> aClass = Class.forName("reflection.data.BasicData");

        Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(String.class);

        declaredConstructor.setAccessible(true);

        Object hello = declaredConstructor.newInstance("hello");
        System.out.println("hello = " + hello);

        Method method1 = aClass.getDeclaredMethod("call");
        method1.invoke(hello);
    }
}
