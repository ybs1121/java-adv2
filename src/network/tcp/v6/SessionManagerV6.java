package network.tcp.v6;

import java.util.ArrayList;
import java.util.List;

public class SessionManagerV6 {

    List<SessionV6> sessions = new ArrayList<SessionV6>();


    public synchronized void addSession(SessionV6 session) {
        sessions.add(session);
    }

    public synchronized void removeSession(SessionV6 session) {
        sessions.remove(session);
    }

    public synchronized void closeAll() {
        for (SessionV6 session : sessions) {
            session.close();
        }
    }

}
