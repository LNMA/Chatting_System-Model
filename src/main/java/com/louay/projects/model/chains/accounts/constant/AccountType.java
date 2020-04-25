package com.louay.projects.model.chains.accounts.constant;

public enum AccountType {
    USER("user"),GROUP("group");

    private String type;

    AccountType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
