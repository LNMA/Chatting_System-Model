package com.louay.projects.model.dao;

import com.louay.projects.model.chains.communications.group.GroupComments;
import com.louay.projects.model.chains.communications.group.GroupPicture;

public interface InsertGroupPostDAO {

    Long insertGroupComments(GroupComments comment);

    int insertGroupPicture(GroupPicture picture);
}
