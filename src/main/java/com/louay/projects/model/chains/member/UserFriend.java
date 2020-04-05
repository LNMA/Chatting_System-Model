package com.louay.projects.model.chains.member;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Scope("prototype")
public class UserFriend {
    private String username;
    private String friend;
    private java.sql.Timestamp friendSince;

    public UserFriend() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    public Timestamp getFriendSince() {
        return friendSince;
    }

    public void setFriendSince(Timestamp friendSince) {
        this.friendSince = friendSince;
    }

    @Override
    public String toString() {
        return "UserFriend{" +
                "username='" + username + '\'' +
                ", friend='" + friend + '\'' +
                ", friendSince=" + friendSince +
                '}';
    }
}
