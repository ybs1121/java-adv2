package reflection;

import reflection.data.Team;
import reflection.data.User;

public class FieldV3 {

    public static void main(String[] args) {
        User user = new User(null, null, "id1");
        Team team = new Team("team1", null);

        System.out.println("===before===");
        System.out.println("user: " + user);
        System.out.println("team: " + team);


        if (user.getId() == null) {
            user.setId("");
        }

        if (team.getName() == null) {
            team.setName("");
        }

        if (team.getId() == null) {
            team.setId("");
        }

        System.out.println("====after====");
        System.out.println("user: " + user);
        System.out.println("team: " + team);
    }
}
