package com.louay.projects.model.dao;

import com.louay.projects.model.chains.accounts.group.Groups;
import com.louay.projects.model.chains.communications.Post;

public interface UpdateGroupPostDAO {

    int updateGroupTextPostByIdPost(Post post);

    int updateGroupImgPostByIdPost(Post post);

    int updateGroupPictureByIdGroup(Groups picture);
}
