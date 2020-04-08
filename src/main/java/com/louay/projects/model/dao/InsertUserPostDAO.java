package com.louay.projects.model.dao;

import com.louay.projects.model.chains.communications.AccountComments;
import com.louay.projects.model.chains.communications.AccountMessage;
import com.louay.projects.model.chains.communications.AccountPicture;


public interface InsertUserPostDAO {

    Long insertAccountComments(AccountComments comments);

    Long insertAccountMassage(AccountMessage message);

    int insertAccountPicture(AccountPicture picture);
}
