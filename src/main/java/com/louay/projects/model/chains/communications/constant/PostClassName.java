package com.louay.projects.model.chains.communications.constant;

public enum PostClassName {
    ACCOUNT_TEX_POST("AccountTextPost"), ACCOUNT_IMG_POST("AccountImgPost"), GROUP_IMG_POST("GroupImgPost"),
    GROUP_TEXT_POST("GroupTextPost");

    private String className;

    PostClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
