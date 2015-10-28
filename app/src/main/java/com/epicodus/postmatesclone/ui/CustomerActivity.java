package com.epicodus.postmatesclone.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.epicodus.postmatesclone.R;
import com.epicodus.postmatesclone.models.CustomerUser;

public class CustomerActivity extends AppCompatActivity {

    private Button mLogoutButton;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        mLogoutButton = (Button) findViewById(R.id.logoutButton);
        mPreferences = getApplicationContext().getSharedPreferences("postmates", Context.MODE_PRIVATE);

        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(CustomerActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}
