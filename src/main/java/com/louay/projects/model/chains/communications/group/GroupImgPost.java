package com.louay.projects.model.chains.communications.group;

import com.louay.projects.model.chains.communications.GroupPost;
import com.louay.projects.model.chains.communications.constant.PostClassName;
import com.louay.projects.model.chains.communications.constant.PostType;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

@Configuration
@Component
@Scope("prototype")
public class GroupImgPost extends GroupPost {
    private java.sql.Blob image;
    private String fileName;

    public GroupImgPost() {
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
        return PostClassName.GROUP_IMG_POST;
    }

    @Override
    public String toString() {
        return super.toString()+", GroupImgPost{" +
                "image=" + image +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
