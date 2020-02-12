package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class loginActivity extends AppCompatActivity {
    private EditText UsernameView;
    private EditText PasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UsernameView=(EditText) findViewById(R.id.username);
        PasswordView=(EditText)findViewById(R.id.password);

        UsernameView.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if(id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL){
                    return true;
                }
                return false;
            }

        });
        PasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    //attemptLogin();

                    return true;
                }
                return false;
            }
        });

        Button UserLogInButton = (Button) findViewById(R.id.login_button); // sign in button

        Button UserSignUpButton = (Button) findViewById(R.id.signup_button); // sign up button


        UserLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        UserSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent= new Intent(getApplicationContext(),SignUpActivity.class);
//                startActivity(intent);
//                finish();
            }
        });
    }



}
