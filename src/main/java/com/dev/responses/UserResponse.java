package com.dev.responses;

import com.dev.objects.User;

public class UserResponse extends BasicResponse{

    private User user;

    public UserResponse(boolean success, Integer errorCode, User user) {
        super(success, errorCode);
        this.user = user;
    }

    public User getUser() {return this.user;}

    public void setUser(User user) {
        this.user = user;
    }
}
