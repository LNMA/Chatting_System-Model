package com.louay.projects.model.dao;

import com.louay.projects.model.chains.accounts.group.Groups;
import com.louay.projects.model.chains.communications.Post;

public interface InsertGroupPostDAO {

    Long insertGroupTextPost(Post post);

    Long insertGroupImgPost(Post post);

    int insertGroupPicture(Groups picture);
}
