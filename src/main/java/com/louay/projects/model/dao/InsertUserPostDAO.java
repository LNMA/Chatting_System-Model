package com.louay.projects.model.dao;

import com.louay.projects.model.chains.accounts.Users;
import com.louay.projects.model.chains.communications.Post;;
import com.louay.projects.model.chains.communications.account.AccountMessage;

public interface InsertUserPostDAO {

    Long insertAccountTextPost(Post post);

    Long insertAccountMassage(AccountMessage message);

    int insertAccountPicture(Users picture);

    Long insertAccountImgPost(Post post);
}
