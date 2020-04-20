package com.louay.projects.model.chains.member.account;

import com.louay.projects.model.chains.accounts.Client;
import com.louay.projects.model.chains.member.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


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
    public String toString() {
        return super.toString()+",FriendRequest{" +
                "sourceAccount=" + sourceAccount +
                '}';
    }
}
