package com.epicodus.postmatesclone.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

/**
 * Created by Guest on 10/28/15.
 */

@Table (name = "CustomerUser", id = "_id")
public class CustomerUser extends Model {

    @Column(name = "Name")
    private String mName;

    @Column(name = "Password")
    private String mPassword;

    public CustomerUser() {
        super();
    }

    public CustomerUser(String name, String password) {
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

    public static CustomerUser find(String username, String password) {
        return new Select()
                .from(CustomerUser.class)
                .where("Name = ?", username)
                .where("Password = ?", password)
                .executeSingle();
    }
}
