package reflection;

import reflection.data.Team;
import reflection.data.User;

public class FieldV4 {

    public static void main(String[] args) throws IllegalAccessException {
        User user = new User(20, null, null);
        Team team = new Team("", null);

        System.out.println("===before===");
        System.out.println("user: " + user);
        System.out.println("team: " + team);

        FieldUtil.nullFieldToDefault(user);
        FieldUtil.nullFieldToDefault(team);

        System.out.println("====after====");
        System.out.println("user: " + user);
        System.out.println("team: " + team);
    }
}
