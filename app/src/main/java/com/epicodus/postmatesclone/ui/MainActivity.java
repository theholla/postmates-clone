package com.epicodus.postmatesclone.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.epicodus.postmatesclone.R;
import com.epicodus.postmatesclone.models.CustomerUser;

public class MainActivity extends AppCompatActivity {

    private EditText mUsernameInput;
    private EditText mPasswordInput;
    private Button mLoginButton;
    private Button mRegisterButton;
    private Button mCompanyLoginButton;
    private SharedPreferences mPreferences;
    private CustomerUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getApplicationContext().getSharedPreferences("postmates", Context.MODE_PRIVATE);

        mUsernameInput = (EditText) findViewById(R.id.newUsername);
        mPasswordInput = (EditText) findViewById(R.id.newPassword);
        mLoginButton = (Button) findViewById(R.id.loginButton);
        mRegisterButton = (Button) findViewById(R.id.registerButton);
        //mCompanyLoginButton = (Button) findViewById(R.id.companyLoginButton);

        if(!isRegistered()) {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsernameInput.getText().toString();
                String password = mPasswordInput.getText().toString();
                CustomerUser currentUser = CustomerUser.find(username, password);
                if (currentUser != null){
                    Toast.makeText(MainActivity.this, "WORKS!!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, CustomerActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "SOMETHING WENT WRONG!!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private boolean isRegistered() {
        String username = mPreferences.getString("username", null);
        String password = mPreferences.getString("password", null);
        if(username == null) {
            return false;
        } else {
            setUser(username, password);
            return true;
        }
    }

    private void setUser(String username, String password) {
        CustomerUser user = CustomerUser.find(username, password);
        if (user != null) {
            mUser = user;
        } else {
            mUser = new CustomerUser(username, password);
            mUser.save();
        }
        Toast.makeText(this, "Hello " + mUser.getName(), Toast.LENGTH_LONG).show();
    }


}
