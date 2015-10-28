package com.epicodus.postmatesclone.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.postmatesclone.R;
import com.epicodus.postmatesclone.models.CustomerUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText mNewUsername;
    private EditText mNewPassword;
    private Button mRegisterButton;
    private SharedPreferences mPreferences;
    private CustomerUser newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mNewUsername = (EditText) findViewById(R.id.newUsername);
        mNewPassword = (EditText) findViewById(R.id.newPassword);
        mRegisterButton = (Button) findViewById(R.id.loginButton);
        mPreferences = getApplicationContext().getSharedPreferences("postmates", Context.MODE_PRIVATE);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNewUsername.getText().toString();
                String password = mNewPassword.getText().toString();
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putString("username", name);
                editor.putString("password", password);
                editor.commit();
                newUser = new CustomerUser(name, password);
                newUser.save();
                Intent intent = new Intent(RegisterActivity.this, CustomerActivity.class);
                startActivity(intent);
            }
        });
    }

}
