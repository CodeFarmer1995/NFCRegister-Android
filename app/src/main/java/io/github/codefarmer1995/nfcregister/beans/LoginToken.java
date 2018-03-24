package io.github.codefarmer1995.nfcregister.beans;

/**
 * Created by Jack on 2018-03-07.
 */

public class LoginToken {
    String email;
    String password;

    public LoginToken(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
