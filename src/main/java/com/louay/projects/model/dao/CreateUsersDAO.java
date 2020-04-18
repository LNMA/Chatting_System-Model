package com.louay.projects.model.dao;

import com.louay.projects.model.chains.accounts.Users;

public interface CreateUsersDAO {
    int insertUser(Users user);

    int insertClientDetail(Users user);

}
