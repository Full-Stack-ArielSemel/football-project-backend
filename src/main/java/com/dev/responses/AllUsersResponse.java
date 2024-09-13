package com.dev.responses;

import com.dev.objects.User;

import java.util.List;

public class AllUsersResponse extends BasicResponse {
    private List<User> users;

    public AllUsersResponse(List<User> users) {
        this.users = users;
    }

    public AllUsersResponse(boolean success, Integer errorCode, List<User> users) {
        super(success, errorCode);
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
