package greenhunan.aircheck;

import greenhunan.aircheck.model.User;

/**
 * Created by Quentin on 8/13/16.
 */
public class GlobalConfig {

    public static final String base_URL = "http://192.168.1.106:9000/"; // TODO: 9/18/16 add '/' at the end
    private static User user;

    public synchronized static User getUser() {
        assert user != null;
        return user;
    }

    public synchronized static void setUser(User user) {
        GlobalConfig.user = user;
    }
}
