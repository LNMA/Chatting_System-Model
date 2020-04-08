package com.louay.projects.model.chains.communications;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Objects;

@Component
@Scope("prototype")
public class AccountPicture {
    private String username;
    private java.sql.Blob picture;
    private String pictureName;
    private java.sql.Timestamp uploadDate;

    public AccountPicture() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Timestamp getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Timestamp uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountPicture)) return false;
        AccountPicture that = (AccountPicture) o;
        return getUsername().compareTo(that.getUsername()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }

    @Override
    public String toString() {
        return "AccountPicture{" +
                "username='" + username + '\'' +
                ", picture=" + picture +
                ", pictureName='" + pictureName + '\'' +
                ", uploadDate=" + uploadDate +
                '}';
    }
}
