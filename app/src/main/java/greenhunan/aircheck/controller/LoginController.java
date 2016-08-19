package greenhunan.aircheck.controller;

import greenhunan.aircheck.GlobalConfig;
import greenhunan.aircheck.model.LoginRequest;
import greenhunan.aircheck.views.LoginCallback;

/**
 * Created by Quentin on 8/13/16.
 */
public class LoginController {

    private LoginRequest request;
    private LoginCallback loginCallback;

    public LoginController(LoginRequest request, LoginCallback loginCallback) {
        this.request = request;
        this.loginCallback = loginCallback;
    }

    public void login(String username, String password) {
        if (request.login(username, password)) {
            loginCallback.onSuccess(request.getRequestInfo());
            GlobalConfig.setUser(request.getUser());
        }
        else
            loginCallback.onFailure(request.getRequestInfo());
    }

}
