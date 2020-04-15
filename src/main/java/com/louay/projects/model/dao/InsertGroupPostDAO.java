package com.louay.projects.model.dao;

import com.louay.projects.model.chains.communications.Post;
import com.louay.projects.model.chains.communications.group.GroupPicture;

public interface InsertGroupPostDAO {

    Long insertGroupTextPost(Post post);

    Long insertGroupImgPost(Post post);

    int insertGroupPicture(GroupPicture picture);
}
