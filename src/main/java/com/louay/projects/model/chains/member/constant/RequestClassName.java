package com.louay.projects.model.chains.member.constant;

public enum RequestClassName {
    FRIEND_REQUEST("FriendRequest") , GROUP_INVITE("GroupInvite"), GROUP_REQUEST("GroupRequest");

    private String requestClassNameValue;

    RequestClassName(String requestClassNameValue) {
        this.requestClassNameValue = requestClassNameValue;
    }

    public String getRequestClassNameValue() {
        return requestClassNameValue;
    }
}
