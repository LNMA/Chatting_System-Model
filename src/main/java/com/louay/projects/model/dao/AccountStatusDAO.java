package com.louay.projects.model.dao;

import com.louay.projects.model.chains.accounts.activity.AccountStatus;
import com.louay.projects.model.chains.accounts.activity.SignInDate;

public interface AccountStatusDAO {

    int insertAccountStatus(AccountStatus status);

    int insertUserSignInDate(SignInDate signInDate);

    int updateAccountStatusByUsername(AccountStatus status);

}
