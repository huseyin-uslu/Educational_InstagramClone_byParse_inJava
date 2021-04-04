package com.firstprojects.instagramclonewithparse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {
EditText usernameText,passText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        usernameText = findViewById(R.id.editTextUserName);
        passText = findViewById(R.id.editTextPass);
        ParseUser signControl = ParseUser.getCurrentUser();
        if(signControl != null) {
            Intent intent = new Intent(this,FeedActivity.class);
            startActivity(intent);
            finish();
        }

    }
    public void signInButton(View view ) {
        ParseUser parseUser = new ParseUser();
        if(usernameText.getText().toString().length() > 0 && passText.getText().toString().length() > 0 ) {
            if(usernameText.getText() != null && passText.getText() != null) {
                parseUser.logInInBackground(usernameText.getText().toString(), passText.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(e!=null) {
                            Toast.makeText(SignUpActivity.this,e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(SignUpActivity.this, "Login is succesful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this,FeedActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });

            }
        }else{
            Toast.makeText(this, "You have to write something!", Toast.LENGTH_SHORT).show();
        }

    }
    public void signUpButton(View view) {
        ParseUser parseUser = new ParseUser();
        if(usernameText.getText().toString().length() > 0 && passText.getText().toString().length() > 0 ) {

                parseUser.setUsername(usernameText.getText().toString());
                parseUser.setPassword(passText.getText().toString());

            parseUser.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if(e!=null){
                        Toast.makeText(SignUpActivity.this,e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(SignUpActivity.this, "Login is succesful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpActivity.this,FeedActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }else {
            Toast.makeText(this, "You have to write something!", Toast.LENGTH_SHORT).show();
        }

    }
}