package com.louay.projects.model.chains.member.constant;

public enum GroupMemberType {
    MASTER("master"), SLAVE("slave");

    private String memberType;

    GroupMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getMemberType() {
        return memberType;
    }
}
