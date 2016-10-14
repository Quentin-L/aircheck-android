package greenhunan.aircheck;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.sax.RootElement;

import greenhunan.aircheck.model.User;

/**
 * Created by Quentin on 8/13/16.
 */
public class GlobalConfig {

    public static final String BASE_URL = "http://localhost:9000/"; // TODO: 9/18/16 add '/' at the end
    private static User user;
    private static String id; // TODO: 10/14/16 even if password and username is stored locally, id needs to be set
    private static boolean userAvailable = false;

    public synchronized static User getUser() {
        assert user != null;
        return user;
    }

    public synchronized static String getId() {
        return id;
    }

    // used when the user types username and password
    public synchronized static void setUser(User user, String id) {
        GlobalConfig.user = user;
        GlobalConfig.id = id;
    }

    public synchronized static void saveUser(Context context) {
        if (user != null) {
            SharedPreferences preferences = getSharedPreferences(context);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(User.USERNAME, user.getUsername());
            editor.putString(User.PASSWORD, user.getPassword());
            editor.apply();
        }
    }

    public synchronized static boolean isUserLocallyAvailable(Context context) {
        User user = retrieveUser(context);
        if (user.getUsername() != null && user.getPassword() != null) {
            userAvailable = true;
            return true;
        } else {
            userAvailable = false;
            return false;
        }
    }

    // called after 'isUserLocallyAvailable' returns true
    public synchronized static User getUser(Context context) {
        checkUserAvailability();
        GlobalConfig.user = retrieveUser(context);
        return GlobalConfig.user;
    }

    public synchronized static void setId(String id) {
        checkUserAvailability();
        GlobalConfig.id = id;
    }

    private static void checkUserAvailability() {
        if (!userAvailable)
            throw new RuntimeException("there is no user stored");
    }

    private static User retrieveUser(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        return new User(preferences.getString(User.USERNAME, null),
                preferences.getString(User.PASSWORD, null));
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

}
