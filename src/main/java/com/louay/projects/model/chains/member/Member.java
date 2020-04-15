package com.louay.projects.model.chains.member;

import java.sql.Timestamp;

public abstract class Member {
    private String friendMember;
    private java.sql.Timestamp friendMemberSince;

    public Member() {
    }

    public String getFriendMember() {
        return friendMember;
    }

    public void setFriendMember(String friendMember) {
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
