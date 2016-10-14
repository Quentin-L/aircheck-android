package greenhunan.aircheck.presenter;

import android.content.Context;

import greenhunan.aircheck.GlobalConfig;
import greenhunan.aircheck.model.User;
import greenhunan.aircheck.service.internet.LoginService;
import greenhunan.aircheck.service.internet.ConnectionService;


/**
 * Created by Quentin on 10/14/16.
 */
public class MainActivityPresenter {

    private Context context;

    public MainActivityPresenter(Context context) {
        this.context = context;
    }

    // used when the app was first opened
    public boolean initialLogin() {
        if (GlobalConfig.isUserLocallyAvailable(context)) {
            User user = GlobalConfig.getUser(context);
            int result = LoginService.login(user);
            switch (result) {
                case ConnectionService.COONECTION_SUCCESS_PASS:
                    return true;
            }
        }
        return false;
    }


}
