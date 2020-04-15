package com.louay.projects.model.chains.member.account;

import com.louay.projects.model.chains.member.Request;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Configuration
@Component
@Scope("prototype")
public class FriendRequest extends Request {
    private String username;


    public FriendRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return super.toString()+", FriendRequest{" +
                "username='" + username + '\'' +
                '}';
    }
}
