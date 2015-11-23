package com.epicodus.postmatesclone.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.epicodus.postmatesclone.R;
import com.facebook.stetho.Stetho;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mSharedPreferences;
    private EditText mUsernameInput, mPasswordInput;
    private Button mLoginButton, mShowRegisterButton, mRegisterButton, mRegisterRoleButton;
    private RadioButton mRadioCustomer, mRadioCompany;
    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);

        mSharedPreferences = getApplicationContext().getSharedPreferences("postmatesapp", Context.MODE_PRIVATE);
        mUsernameInput = (EditText) findViewById(R.id.newUsername);
        mPasswordInput = (EditText) findViewById(R.id.newPassword);
        mLoginButton = (Button) findViewById(R.id.btnLogin);
        mShowRegisterButton = (Button) findViewById(R.id.btnShowRegister);
        mRegisterButton = (Button) findViewById(R.id.btnRegister);
        mRadioCustomer = (RadioButton) findViewById(R.id.radioCustomer);
        mRadioCompany = (RadioButton) findViewById(R.id.radioCompany);
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);


        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = mUsernameInput.getText().toString();
                final String password = mPasswordInput.getText().toString();

                // TODO: Make model for user

                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            goToMainPage();
                        } else if (user == null) {
                            showErrorDialog(getString(R.string.username_password_error));
                        }
                        else {
                            showErrorDialog(getString(R.string.generic_error));
                        }
                    }
                });
                }
        });

        mShowRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginButton.setVisibility(View.INVISIBLE);
                mRegisterButton.setVisibility(View.VISIBLE);
                mRadioGroup.setVisibility(View.VISIBLE);
                mShowRegisterButton.setVisibility(View.INVISIBLE);
            }
        });

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = mUsernameInput.getText().toString();
                final String password = mPasswordInput.getText().toString();

                if(! mRadioCompany.isChecked() && !mRadioCustomer.isChecked()) {
                    showErrorDialog(getString(R.string.user_error));
                }
                else {
                    int radioChoice = mRadioGroup.getCheckedRadioButtonId();
                    RadioButton choice = (RadioButton) findViewById(radioChoice);
                    String role = choice.getText().toString();


                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.putString("role", role);
                    editor.apply();

                    registerUser(username, password, role);
                    ParseUser.logInInBackground(username, password);
                }
            }
        });
    }


    public void goToMainPage() {
        Intent intent = new Intent(this, StoreActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void registerUser(String username, String password, String role) {
        if (username.isEmpty() || password.isEmpty()) {
            showErrorDialog(getString(R.string.registration_text_error));
        } else {
            ParseUser newUser = getParseUser(username, password);
            newUser.put("role", role);

            newUser.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    setProgressBarIndeterminateVisibility(false);

                    if (e == null) {
                        // Success!
                        goToMainPage();
                    } else {
                        showErrorDialog(getString(R.string.registration_error));
                    }
                }
            });
        }
    }

    @NonNull
    private ParseUser getParseUser(String username, String password) {
        setProgressBarIndeterminateVisibility(true);

        ParseUser newUser = new ParseUser();
        newUser.setUsername(username);
        newUser.setPassword(password);
        return newUser;
    }

    private void showErrorDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(message)
                .setTitle("Oops!")
                .setPositiveButton(android.R.string.ok, null)
                .create()
                .show();
    }
}

