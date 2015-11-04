package com.epicodus.postmatesclone.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceScreen;
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

        String username = mPreferences.getString("username", null);
        if(username != null) {
            Intent intent = new Intent(this, CustomerActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "PLEASE LOGIN", Toast.LENGTH_LONG).show();
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
                        setUser(username, password);
                        Toast.makeText(MainActivity.this, "WORKS!!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, CustomerActivity.class);
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


    private void setUser(String username, String password) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.commit();
    }


}
