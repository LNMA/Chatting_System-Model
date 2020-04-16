package com.louay.projects.model.chains.communications;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Configuration
@Component
@Scope("prototype")
public class GroupPost extends Post{
    private String idGroup;
    private String username;

    public GroupPost() {
    }

    public String getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(String idGroup) {
        this.idGroup = idGroup;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return super.toString()+", GroupPost{" +
                "idGroup='" + idGroup + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
