package com.louay.projects.model.dao;

import com.louay.projects.model.chains.communications.group.GroupTextPost;
import com.louay.projects.model.chains.communications.group.GroupPicture;

public interface InsertGroupPostDAO {

    Long insertGroupTextPost(GroupTextPost post);

    int insertGroupPicture(GroupPicture picture);
}
