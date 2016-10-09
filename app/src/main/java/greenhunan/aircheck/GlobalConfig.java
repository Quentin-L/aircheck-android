package greenhunan.aircheck;

import greenhunan.aircheck.model.User;

/**
 * Created by Quentin on 8/13/16.
 */
public class GlobalConfig {

    public static final String base_URL = "http://localhost:9000/"; // TODO: 9/18/16 add '/' at the end
    private static User user;
    private static String id;

    public synchronized static User getUser() {
        assert user != null;
        return user;
    }

    public synchronized static String getId() {
        return id;
    }

    public synchronized static void setUser(User user, String id) {
        GlobalConfig.user = user;
        GlobalConfig.id = id;
    }
}
