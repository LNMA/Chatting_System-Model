package com.louay.projects.model.constants;

public enum  GroupPrivacy {
    PUBLIC("public"), PRIVATE("private"), HIDDEN("hidden");

    private String privacy;

    GroupPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public String getPrivacy() {
        return privacy;
    }
}
