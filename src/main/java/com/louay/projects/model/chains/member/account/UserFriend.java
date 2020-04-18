package com.louay.projects.model.chains.member.account;

import com.louay.projects.model.chains.member.Member;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Configuration
@Component
@Scope("prototype")
public class UserFriend extends Member {
    private String username;


    public UserFriend() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return super.toString()+", UserFriend{" +
                "username='" + username + '\'' +
                '}';
    }
}
