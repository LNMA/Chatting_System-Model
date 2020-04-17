package com.louay.projects.model.chains.accounts;

import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Base64;

public abstract class Accounts {
    private java.sql.Blob picture;
    private String pictureName;
    private java.sql.Timestamp dateCreate;

    public Accounts() {
    }

    public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public StringBuilder getBase64(){
        StringBuilder stringBase46 = new StringBuilder();
        int size;
        try {
            size = (int) this.picture.length();
            stringBase46.append(Base64.getEncoder().encodeToString(this.picture.getBytes(1, size)));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return stringBase46;
    }

    public Timestamp getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Timestamp dateCreate) {
        this.dateCreate = dateCreate;
    }

    public abstract AccountType getAccountType();

    public abstract AccountClassName getAccountClassName();

    @Override
    public String toString() {
        return "Accounts{" +
                "picture=" + picture +
                ", pictureName='" + pictureName + '\'' +
                ", dateCreate=" + dateCreate +
                '}';
    }
}
