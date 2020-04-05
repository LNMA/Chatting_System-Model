package com.louay.projects.model.chains.users;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Admin extends Users {

    public Admin() {
    }

}
