package com.epicodus.postmatesclone.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.postmatesclone.R;
import com.facebook.stetho.Stetho;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    private EditText mUsernameInput;
    private EditText mPasswordInput;
    private Button mLoginButton;
    private Button mRegisterButton;
    private Button mCompanyLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);

        mUsernameInput = (EditText) findViewById(R.id.newUsername);
        mPasswordInput = (EditText) findViewById(R.id.newPassword);
        mLoginButton = (Button) findViewById(R.id.btnRegisterCustomer);
        mRegisterButton = (Button) findViewById(R.id.registerButton);
        //mCompanyLoginButton = (Button) findViewById(R.id.companyLoginButton);

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
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            Intent intent = new Intent(MainActivity.this, StoreActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            AlertDialog show = new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("Message")
                                    .setMessage("Error: username or password wrong")
                                    .setNeutralButton("OK", null)
                                    .show();
                        }
                    }
                });
                }
        });


    }


}
