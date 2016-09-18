package greenhunan.aircheck.model;

/**
 * Created by Quentin on 8/13/16.
 */
public class User {

    private String username;
    private String password;
    private String ID;

    public User() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        assert password != null;
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        assert username != null;
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
