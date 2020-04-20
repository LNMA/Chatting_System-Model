package com.louay.projects.model.chains.member;

import com.louay.projects.model.chains.accounts.Client;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

public abstract class Member {
    @Autowired
    private Client friendMember;
    private java.sql.Timestamp friendMemberSince;

    public Member() {
    }

    public Client getFriendMember() {
        return friendMember;
    }

    public void setFriendMember(Client friendMember) {
        this.friendMember = friendMember;
    }

    public Timestamp getFriendMemberSince() {
        return friendMemberSince;
    }

    public void setFriendMemberSince(Timestamp friendMemberSince) {
        this.friendMemberSince = friendMemberSince;
    }

    @Override
    public String toString() {
        return "Member{" +
                "friendMember='" + friendMember + '\'' +
                ", friendMemberSince=" + friendMemberSince +
                '}';
    }
}
