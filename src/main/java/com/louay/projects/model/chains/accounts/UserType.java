package com.louay.projects.model.chains.accounts;

public enum UserType {
    ADMIN("admin"), CLIENT("client"), SUPERVISOR("supervisor");
    private String type;

    UserType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
