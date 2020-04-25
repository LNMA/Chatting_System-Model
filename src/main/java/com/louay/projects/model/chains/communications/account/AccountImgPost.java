package com.louay.projects.model.chains.communications.account;

import com.louay.projects.model.chains.communications.Post;
import com.louay.projects.model.chains.communications.constant.PostClassName;
import com.louay.projects.model.chains.communications.constant.PostType;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Objects;

@Configuration
@Component
@Scope("prototype")
public class AccountImgPost extends Post {
    java.sql.Blob image;
    String fileName;

    public AccountImgPost() {
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

    public StringBuilder getBase64(){
        StringBuilder stringBase46 = new StringBuilder();
        int size;
        try {
            size = (int) this.image.length();
            stringBase46.append(Base64.getEncoder().encodeToString(this.image.getBytes(1, size)));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return stringBase46;
    }

    @Override
    public PostType getType() {
        return PostType.IMG_POST;
    }

    @Override
    public PostClassName getClassName() {
        return PostClassName.ACCOUNT_IMG_POST;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountImgPost)) return false;
        if (!super.equals(o)) return false;
        AccountImgPost imgPost = (AccountImgPost) o;
        return getImage() == (imgPost.getImage()) &&
                getFileName().compareTo(imgPost.getFileName()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getImage(), getFileName());
    }

    @Override
    public String toString() {
        return super.toString()+", AccountImgPost{" +
                "Image=" + image +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
