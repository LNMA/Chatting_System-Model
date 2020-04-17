package com.louay.projects.model.dao;

import com.louay.projects.model.chains.accounts.Users;
import com.louay.projects.model.chains.communications.Post;
import com.louay.projects.model.chains.communications.account.AccountMessage;

public interface UpdateUserPostDAO {

    int updateAccountTextPostByIdPost(Post post);

    int updateAccountMassageByIdMessage(AccountMessage message);

    int updateAccountPictureByUsername(Users picture);

    int updateAccountImgPostByIdPost(Post post);

}
