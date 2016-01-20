package tazk_2.profiles;

/**
 * Created by whoosh on 12/21/15.
 */
public class UserProfile {

    private final String login;
    private final String pass;

    public UserProfile(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public UserProfile(String login) {
        this.login = login;
        this.pass = login;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }
}