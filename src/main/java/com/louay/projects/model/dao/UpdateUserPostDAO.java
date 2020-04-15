package com.louay.projects.model.dao;

import com.louay.projects.model.chains.communications.Post;
import com.louay.projects.model.chains.communications.account.AccountMessage;
import com.louay.projects.model.chains.communications.account.AccountPicture;

public interface UpdateUserPostDAO {

    int updateAccountTextPostByIdComment(Post post);

    int updateAccountMassageByIdMessage(AccountMessage message);

    int updateAccountPictureByUsername(AccountPicture picture);

    int updateAccountImgPost(Post post);

}
