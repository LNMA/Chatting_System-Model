package com.louay.projects.model.chains.communications.group;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Objects;

public class GroupImgPost {
    Long idPost;
    String idGroup;
    String username;
    java.sql.Blob image;
    String fileName;
    java.sql.Timestamp dateUpload;

    public GroupImgPost() {
    }

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }

    public String getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(String idGroup) {
        this.idGroup = idGroup;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Timestamp getDateUpload() {
        return dateUpload;
    }

    public void setDateUpload(Timestamp dateUpload) {
        this.dateUpload = dateUpload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupImgPost)) return false;
        GroupImgPost that = (GroupImgPost) o;
        return getIdPost().compareTo(that.getIdPost()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdPost());
    }

    @Override
    public String toString() {
        return "GroupImgPost{" +
                "idPost=" + idPost +
                ", idGroup='" + idGroup + '\'' +
                ", username='" + username + '\'' +
                ", image=" + image +
                ", fileName='" + fileName + '\'' +
                ", dateUpload=" + dateUpload +
                '}';
    }
}
