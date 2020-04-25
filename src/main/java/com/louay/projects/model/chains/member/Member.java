package com.louay.projects.model.chains.member;

import com.louay.projects.model.chains.accounts.Client;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public abstract class Member implements Comparable<Member>, Serializable {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        Member member = (Member) o;
        return getFriendMember().compareTo(member.getFriendMember()) == 0 &&
                getFriendMemberSince().compareTo(member.getFriendMemberSince()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFriendMember(), getFriendMemberSince());
    }

    @Override
    public int compareTo(Member o) {
        if (this.friendMemberSince != null && o.getFriendMemberSince() != null) {
            java.sql.Timestamp date1 = this.friendMemberSince;
            java.sql.Timestamp date2 = o.getFriendMemberSince();

            if (date1.after(date2)) {
                return -1;
            }
            if (date1.before(date2)) {
                return 1;
            }
        }
        return 0;    }

    @Override
    public String toString() {
        return "Member{" +
                "friendMember='" + friendMember + '\'' +
                ", friendMemberSince=" + friendMemberSince +
                '}';
    }
}
