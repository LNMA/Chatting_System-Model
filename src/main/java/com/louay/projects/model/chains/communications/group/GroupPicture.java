package com.louay.projects.model.chains.communications.group;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Objects;

@Component
@Scope("prototype")
public class GroupPicture {
    private String idGroup;
    private java.sql.Blob picture;
    private String pictureName;
    private java.sql.Timestamp uploadDate;

    public GroupPicture() {
    }

    public String getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(String idGroup) {
        this.idGroup = idGroup;
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
        if (!(o instanceof GroupPicture)) return false;
        GroupPicture picture = (GroupPicture) o;
        return getIdGroup().compareTo(picture.getIdGroup()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdGroup());
    }

    @Override
    public String toString() {
        return "GroupPicture{" +
                "idGroup='" + idGroup + '\'' +
                ", picture=" + picture +
                ", pictureName='" + pictureName + '\'' +
                ", uploadDate=" + uploadDate +
                '}';
    }
}
