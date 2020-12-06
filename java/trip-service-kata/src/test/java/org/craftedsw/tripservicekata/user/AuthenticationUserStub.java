package org.craftedsw.tripservicekata.user;
import org.craftedsw.tripservicekata.user.AuthenticationUser;

public class AuthenticationUserStub implements AuthenticationUser {
    private User loggedUser;

    public AuthenticationUserStub(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    @Override
    public User getLoggedUser() {
        return loggedUser;
    }
}
