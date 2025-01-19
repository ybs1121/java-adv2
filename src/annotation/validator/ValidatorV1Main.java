package annotation.validator;

import static util.MyLogger.log;

public class ValidatorV1Main {

    public static void main(String[] args) {
        User user = new User("user1", 0);
        Team team = new Team("", 0);

        try {
            log("== user 검증 ==");
            validateUser(user);
        } catch (Exception e) {
            log(e);
        }


        try {
            log("== team 검증 ==");
            validateTeam(team);
        } catch (Exception e) {
            log(e);
        }

    }

    private static void validateTeam(Team team) {
        if (team.getName() == null || team.getName().equals("")) {
            throw new RuntimeException("이름이 비어있다.");
        }

        if (team.getMemberCount() < 1 || team.getMemberCount() > 999) {
            throw new RuntimeException("1~999 사이여야합니다.");
        }

    }

    private static void validateUser(User user) {
        if (user.getName() == null || user.getName().equals("")) {
            throw new RuntimeException("이름이 비어있다.");
        }

        if (user.getAge() < 1 || user.getAge() > 100) {
            throw new RuntimeException("1~100 사이여야합니다.");
        }

    }


}
