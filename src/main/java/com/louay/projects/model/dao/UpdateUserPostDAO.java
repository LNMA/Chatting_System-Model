package com.louay.projects.model.dao;

import com.louay.projects.model.chains.communications.AccountImgPost;
import com.louay.projects.model.chains.communications.AccountTextPost;
import com.louay.projects.model.chains.communications.AccountMessage;
import com.louay.projects.model.chains.communications.AccountPicture;

public interface UpdateUserPostDAO {

    int updateAccountTextPostByIdComment(AccountTextPost post);

    int updateAccountMassageByIdMessage(AccountMessage message);

    int updateAccountPictureByUsername(AccountPicture picture);

    int updateAccountImgPost(AccountImgPost post);

}
