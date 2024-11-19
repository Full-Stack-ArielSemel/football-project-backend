package com.dev.responses;

import com.dev.models.User;

public class LoginResponse extends BasicResponse{
    private User user;

    public LoginResponse(boolean success, Integer errorCode, User user) {
        super(success, errorCode);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
