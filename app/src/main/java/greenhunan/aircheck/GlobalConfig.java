package greenhunan.aircheck;

import greenhunan.aircheck.model.User;

/**
 * Created by Quentin on 8/13/16.
 */
public class GlobalConfig {
    private static User user;

    public static User getUser() {
        assert user != null;
        return user;
    }

    public static void setUser(User user) {
        GlobalConfig.user = user;
    }
}
