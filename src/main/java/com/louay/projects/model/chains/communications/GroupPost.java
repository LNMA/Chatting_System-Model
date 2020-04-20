package com.louay.projects.model.chains.communications;

import com.louay.projects.model.chains.accounts.group.Groups;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GroupPost extends Post {
    @Autowired
    private Groups groups;

    public GroupPost() {
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return super.toString()+", GroupPost{" +
                "groups=" + groups +
                '}';
    }
}
