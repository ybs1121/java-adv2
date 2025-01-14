package reflection;

import reflection.data.BasicData;

import java.lang.reflect.Modifier;
import java.util.Arrays;

public class BasicV2 {

    public static void main(String[] args) {
        Class<BasicData> basicDataClass = BasicData.class;

        System.out.println("basicData.getName() = " + basicDataClass.getName());
        System.out.println("basicData.getSimpleName() = " + basicDataClass.getSimpleName());
        System.out.println("basicData.getPackage() = " + basicDataClass.getPackage());
        System.out.println("basicData.getSuperclass() = " + basicDataClass.getSuperclass());
        System.out.println("basicData.getInterfaces() = " + Arrays.toString(basicDataClass.getInterfaces()));

        System.out.println("basicData.getInterfaces() = " + basicDataClass.isInterface());
        System.out.println("basicData.getInterfaces() = " + basicDataClass.isEnum());
        System.out.println("basicData.getInterfaces() = " + basicDataClass.isAnnotation());

        int modifiers = basicDataClass.getModifiers();

        System.out.println("basicData.getModifiers() = " + modifiers);
        System.out.println("isPublic = " + Modifier.isPublic(modifiers));
        System.out.println("Modifier.toString = " + Modifier.toString(modifiers));



    }
}
