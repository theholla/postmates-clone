package com.epicodus.postmatesclone.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.postmatesclone.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterActivity extends AppCompatActivity {

    private EditText mNewUsername;
    private EditText mNewPassword;
    private Button mRegisterCustomerButton;
    private Button mRegisterCompanyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mNewUsername = (EditText) findViewById(R.id.newUsername);
        mNewPassword = (EditText) findViewById(R.id.newPassword);
        mRegisterCustomerButton = (Button) findViewById(R.id.btnRegisterCustomer);
        mRegisterCompanyButton = (Button) findViewById(R.id.btnRegisterCompany);

        mRegisterCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mNewUsername.getText().toString().trim();
                String password = mNewPassword.getText().toString().trim();
                String role = "customer";

                if (username.isEmpty() || password.isEmpty()) {
                    showErrorDialog();
                } else {
                    ParseUser newUser = getParseUser(username, password);
                    newUser.put("role", role);

                    newUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            setProgressBarIndeterminateVisibility(false);

                            if (e == null) {
                                // Success!
                                Intent intent = new Intent(RegisterActivity.this, StoreActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else showErrorDialog();
                        }
                    });
                }
            }
        });

        mRegisterCompanyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mNewUsername.getText().toString().trim();
                String password = mNewPassword.getText().toString().trim();
                String role = "admin";

                if (username.isEmpty() || password.isEmpty()) {
                    showErrorDialog();
                } else {
                    ParseUser newUser = getParseUser(username, password);
                    newUser.put("role", role);

                    newUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            setProgressBarIndeterminateVisibility(false);

                            if (e == null) {
                                // Success!
                                Intent intent = new Intent(RegisterActivity.this, CompanyActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else showErrorDialog();
                        }
                    });
                }
            }
        });
    }

    @NonNull
    private ParseUser getParseUser(String username, String password) {
        setProgressBarIndeterminateVisibility(true);

        ParseUser newUser = new ParseUser();
        newUser.setUsername(username);
        newUser.setPassword(password);
        return newUser;
    }

    private void showErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
        builder.setMessage("This registration won't work for us.")
                .setTitle("Oops!")
                .setPositiveButton(android.R.string.ok, null)
                .create()
                .show();
    }
}
