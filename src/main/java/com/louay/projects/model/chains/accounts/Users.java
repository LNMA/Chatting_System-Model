package com.louay.projects.model.chains.accounts;

import com.louay.projects.model.chains.accounts.constant.UserType;

import java.util.Objects;

public abstract class Users extends Accounts{
    private String username;
    private String password;
    private String accountPermission;

    public Users() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountPermission() {
        return accountPermission;
    }

    public void setAccountPermission(String accountPermission) {
        this.accountPermission = accountPermission;
    }

    public abstract UserType getUserType();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return this.getUsername().compareTo(users.getUsername()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }

    @Override
    public String toString() {
        return super.toString()+", Users{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accountPermission='" + accountPermission + '\'' +
                '}';
    }
}
