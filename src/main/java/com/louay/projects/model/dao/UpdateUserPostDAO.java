package com.louay.projects.model.dao;

import com.louay.projects.model.chains.communications.AccountComments;
import com.louay.projects.model.chains.communications.AccountMessage;
import com.louay.projects.model.chains.communications.AccountPicture;

public interface UpdateUserPostDAO {

    int updateAccountCommentsByIdComment(AccountComments comments);

    int updateAccountMassageByIdMessage(AccountMessage message);

    int updateAccountPictureByIdPicture(AccountPicture picture);
}
