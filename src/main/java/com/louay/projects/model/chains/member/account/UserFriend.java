package com.louay.projects.model.chains.member.account;

import com.louay.projects.model.chains.accounts.Client;
import com.louay.projects.model.chains.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;


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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserFriend)) return false;
        if (!super.equals(o)) return false;
        UserFriend that = (UserFriend) o;
        return getUser().compareTo(that.getUser()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUser());
    }

    @Override
    public String toString() {
        return super.toString()+",UserFriend{" +
                "user=" + user +
                '}';
    }
}
