package greenhunan.aircheck.service;

import org.junit.Before;

import greenhunan.aircheck.model.User;
import greenhunan.aircheck.service.internet.ConnectionService;
import greenhunan.aircheck.service.internet.LoginService;

import static org.junit.Assert.*;

/**
 * Created by Quentin on 9/20/16.
 */
public class LoginServiceTest {

    User user;
    LoginService service;

    @Before
    public void setup() {
        user = new User("admin", "admin");
        service = new LoginService();
    }

    @org.junit.Test
    public void testLogin() throws Exception {
        int result = service.login(user);
        Thread.sleep(5000);
        assertEquals(ConnectionService.COONECTION_SUCCESS_PASS, result);
    }
}