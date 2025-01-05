package chat.server;

import chat.server.command.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandManagerV3 implements CommandManager {

    private static final String DELIMITER = "\\|";
    private final SessionManager sessionManager;
    private final Map<String, Command> commands = new HashMap<>();

    public CommandManagerV3(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        commands.put("/join", new JoinCommand(sessionManager));
        commands.put("/exit", new ExitCommand(sessionManager));
        commands.put("/message", new MessageCommand(sessionManager));
        commands.put("/users", new UsersCommand(sessionManager));
        commands.put("/change", new ChangeCommand(sessionManager));

    }

    @Override
    public void execute(String totalMessage, Session session) throws IOException {
        String[] args = totalMessage.split(DELIMITER);
        String command = args[0];
        Command commandObj = commands.get(command);
        if (commandObj == null) {
            session.send("처리할 수 없는 명령어입니다. " + totalMessage);
            return;
        }
        commandObj.execute(args, session);
    }
}
