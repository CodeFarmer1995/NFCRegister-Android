package io.github.codefarmer1995.nfcregister.beans;

/**
 * Created by Jack on 2018-03-16.
 */

public class RegisterRequestBody {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RegisterRequestBody(User user) {
        this.user = user;
    }
}
