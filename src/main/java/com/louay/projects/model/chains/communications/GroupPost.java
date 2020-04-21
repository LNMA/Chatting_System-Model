package com.louay.projects.model.chains.communications;

import com.louay.projects.model.chains.accounts.group.Groups;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupPost)) return false;
        if (!super.equals(o)) return false;
        GroupPost groupPost = (GroupPost) o;
        return getGroups().compareTo(groupPost.getGroups()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getGroups());
    }

    @Override
    public String toString() {
        return super.toString()+", GroupPost{" +
                "groups=" + groups +
                '}';
    }
}
