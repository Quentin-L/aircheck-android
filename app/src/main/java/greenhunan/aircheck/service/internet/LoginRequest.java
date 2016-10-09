package greenhunan.aircheck.service.internet;

import greenhunan.aircheck.model.User;

/**
 * Created by Quentin on 9/28/16.
 */
public interface LoginRequest {
    int login(User user);
}
