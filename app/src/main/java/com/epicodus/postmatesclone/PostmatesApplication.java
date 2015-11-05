package com.epicodus.postmatesclone;

import android.content.Intent;
import android.os.Bundle;

import com.activeandroid.app.Application;
import com.epicodus.postmatesclone.ui.MainActivity;
import com.epicodus.postmatesclone.ui.StoreActivity;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class PostmatesApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "KhGYpNJq6QsrcVL2VEP2t2AUpWx8uC7JJJjccyb3", "09yg0hJuq1rSnP4NsYjo9sXa1YNGNZXY72V79cts");

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
           goToMainPage();
        }
    }

    private void goToMainPage() {
        Intent intent = new Intent(PostmatesApplication.this, StoreActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
