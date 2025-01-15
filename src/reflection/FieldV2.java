package reflection;

import reflection.data.User;

import java.lang.reflect.Field;

public class FieldV2 {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        User user = new User(20, "userA", "id1");
        System.out.println("기존 이름  = " + user.getName());

        Class<? extends User> aClass = user.getClass();

        Field name = aClass.getDeclaredField("name");

        //private 필드에 접근 허용, private 메서드도 호출 가능
        name.setAccessible(true);
        name.set(user, "userB");
        System.out.println("변경된 이름 : " + user.getName());
    }
}
