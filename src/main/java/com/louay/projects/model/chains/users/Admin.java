package com.louay.projects.model.chains.users;


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
    public String toString() {
        return super.toString() + ", Admin{}";
    }
}
