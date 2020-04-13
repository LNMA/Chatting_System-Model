package com.louay.projects.model.dao;

import com.louay.projects.model.chains.communications.group.GroupTextPost;
import com.louay.projects.model.chains.communications.group.GroupPicture;

public interface UpdateGroupPostDAO {

    int updateGroupTextPostByIdComment(GroupTextPost post);

    int updateGroupPictureByIdGroup(GroupPicture picture);
}
