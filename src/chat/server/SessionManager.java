package chat.server;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static util.MyLogger.log;

public class SessionManager {


    List<Session> sessions = new ArrayList<Session>();


    public synchronized void addSession(Session session) {
        sessions.add(session);
    }

    public synchronized void removeSession(Session session) {
        sessions.remove(session);
    }

    public synchronized void closeAll() {
        for (Session session : sessions) {
            session.close();
        }
    }

    public synchronized void sendAll(String received) {
        for (Session session : sessions) {
            try {
                session.send(received);
            } catch (IOException e) {
                log(e);
            }
        }
    }

    public synchronized List<String> getAllUsername() {
        List<String> usernames = new ArrayList<>();
        for (Session session : sessions) {
            if (session.getUsername() != null) {
                usernames.add(session.getUsername());
            }
        }

        return usernames;
    }
}
