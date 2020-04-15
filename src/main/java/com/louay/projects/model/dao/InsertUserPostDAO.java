package com.louay.projects.model.dao;

import com.louay.projects.model.chains.communications.Post;;
import com.louay.projects.model.chains.communications.account.AccountMessage;
import com.louay.projects.model.chains.communications.account.AccountPicture;

public interface InsertUserPostDAO {

    Long insertAccountTextPost(Post post);

    Long insertAccountMassage(AccountMessage message);

    int insertAccountPicture(AccountPicture picture);

    Long insertAccountImgPost(Post post);
}
