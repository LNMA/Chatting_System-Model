package com.louay.projects.model.chains.accounts.constant;

public enum AccountClassName {
    ADMIN("Admin"), CLIENT("Client"), GROUPS("Groups");

    private String className;

    AccountClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
