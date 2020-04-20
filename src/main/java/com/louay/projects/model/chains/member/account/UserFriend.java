package com.louay.projects.model.chains.member.account;

import com.louay.projects.model.chains.accounts.Client;
import com.louay.projects.model.chains.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Configuration
@Component
@Scope("prototype")
public class UserFriend extends Member {
    @Autowired
    private Client user;


    public UserFriend() {
    }

    public Client getUser() {
        return user;
    }

    public void setUser(Client user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return super.toString()+",UserFriend{" +
                "user=" + user +
                '}';
    }
}
