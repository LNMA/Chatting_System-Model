package com.louay.projects.model.dao;

import com.louay.projects.model.chains.communications.group.GroupComments;
import com.louay.projects.model.chains.communications.group.GroupPicture;

public interface UpdateGroupPostDAO {

    int updateGroupCommentsByIdComment(GroupComments comment);

    int updateGroupPictureByIdPicture(GroupPicture picture);
}
