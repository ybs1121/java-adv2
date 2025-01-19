package annotation.validator;

public class User {

    @NotEmpty(message = "이름이 비어있습니다.")
    private String name;
    @Range(min = 1, max = 100)
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
