package com.louay.projects.model.dao;

import com.louay.projects.model.chains.communications.group.GroupImgPost;
import com.louay.projects.model.chains.communications.group.GroupTextPost;
import com.louay.projects.model.chains.communications.group.GroupPicture;

public interface UpdateGroupPostDAO {

    int updateGroupTextPostByIdComment(GroupTextPost post);

    int updateGroupImgPostByIdComment(GroupImgPost post);

    int updateGroupPictureByIdGroup(GroupPicture picture);
}
