package sample;

import java.util.ArrayList;
import java.util.Objects;

public class User {
    private String identity;
    private String login;
    private String password;

    public User(String identity, String login, String password) {
        this.identity = identity;
        this.login = login;
        this.password = password;
    }

    public String getIdentity() {
        return identity;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(identity, user.identity) && Objects.equals(login, user.login) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identity, login, password);
    }
}
