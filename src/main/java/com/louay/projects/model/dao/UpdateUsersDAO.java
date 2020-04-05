package com.louay.projects.model.dao;

import com.louay.projects.model.chains.users.Users;

public interface UpdateUsersDAO {

    int updateUserByUsername(Users user);

    int updateClientDetailByUsername(Users user);

}
