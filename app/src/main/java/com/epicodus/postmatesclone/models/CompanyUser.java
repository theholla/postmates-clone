package com.epicodus.postmatesclone.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Guest on 10/28/15.
 */

@Table(name = "CompanyUser", id = "_id")
public class CompanyUser extends Model {

    @Column(name = "Name")
    private String mName;

    @Column(name = "Password")
    private String mPassword;

    public CompanyUser() {
        super();
    }

    public CompanyUser(String name, String password) {
        super();
        mName = name;
        mPassword = password;
    }

    public String getName() {
        return mName;
    }

    public String getPassword() {
        return mPassword;
    }
}
