package greenhunan.aircheck.service;

import greenhunan.aircheck.model.User;

/**
 * Created by Quentin on 8/13/16.
 * <p/>
 * handles requests for LoginActivity
 */
public class LoginRequest {

    private User user;
    private String requestInfo;

    public boolean login(String username, String password) {
        // TODO: 8/13/16 internet request, use post method; store {@code User} object
        user = getUserInfo();
        return false;
    }

    public User getUser() {
        return user;
    }

    public String getRequestInfo() {
        assert requestInfo != null;
        return requestInfo;
    }

    private User getUserInfo() {
        // TODO: 8/13/16 return UserInfo: use JSON?
        return null;
    }


}
