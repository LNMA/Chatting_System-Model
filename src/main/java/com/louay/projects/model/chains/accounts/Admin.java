package com.louay.projects.model.chains.accounts;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Configuration
@Component
@Scope("prototype")
public class Admin extends Users {

    public Admin() {
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.USER;
    }

    @Override
    public UserType getUserType() {
        return UserType.ADMIN;
    }

    @Override
    public AccountClassName getAccountClassName() {
        return AccountClassName.ADMIN;
    }

    @Override
    public String toString() {
        return super.toString() + ", Admin{}";
    }
}
