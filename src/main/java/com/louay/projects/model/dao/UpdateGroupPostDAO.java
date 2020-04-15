package com.louay.projects.model.dao;

import com.louay.projects.model.chains.communications.Post;
import com.louay.projects.model.chains.communications.group.GroupPicture;

public interface UpdateGroupPostDAO {

    int updateGroupTextPostByIdComment(Post post);

    int updateGroupImgPostByIdComment(Post post);

    int updateGroupPictureByIdGroup(GroupPicture picture);
}
