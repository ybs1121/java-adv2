package annotation.java;

public class DeprecatedClass {

    public void call1() {
        System.out.println("DEPRECATED CLASS call");
    }

    @Deprecated
    public void call2() {
        System.out.println("DEPRECATED CLASS call2");
    }

    @Deprecated(since = "2.4", forRemoval = true)
    public void call3() {
        System.out.println("DEPRECATED CLASS call3");
    }
}
