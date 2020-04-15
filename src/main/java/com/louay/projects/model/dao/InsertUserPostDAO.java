package com.louay.projects.model.dao;

import com.louay.projects.model.chains.communications.AccountImgPost;
import com.louay.projects.model.chains.communications.AccountTextPost;
import com.louay.projects.model.chains.communications.AccountMessage;
import com.louay.projects.model.chains.communications.AccountPicture;


public interface InsertUserPostDAO {

    Long insertAccountTextPost(AccountTextPost post);

    Long insertAccountMassage(AccountMessage message);

    int insertAccountPicture(AccountPicture picture);

    Long insertAccountImgPost(AccountImgPost post);
}
