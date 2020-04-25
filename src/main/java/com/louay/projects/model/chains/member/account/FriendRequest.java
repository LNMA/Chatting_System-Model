package com.louay.projects.model.chains.member.account;

import com.louay.projects.model.chains.accounts.Client;
import com.louay.projects.model.chains.member.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Configuration
@Component
@Scope("prototype")
public class FriendRequest extends Request {
    @Autowired
    private Client sourceAccount;


    public FriendRequest() {
    }

    public Client getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Client sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FriendRequest)) return false;
        if (!super.equals(o)) return false;
        FriendRequest that = (FriendRequest) o;
        return getSourceAccount().compareTo(that.getSourceAccount()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSourceAccount());
    }

    @Override
    public String toString() {
        return super.toString()+",FriendRequest{" +
                "sourceAccount=" + sourceAccount +
                '}';
    }
}
