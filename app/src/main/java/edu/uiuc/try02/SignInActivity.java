package edu.uiuc.try02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import java.util.Iterator;

public class SignInActivity extends AppCompatActivity {
    ThemeSharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sharedPref = new ThemeSharedPref(this);
        Log.e("themeeee", "onCreate:"+String.valueOf(sharedPref.getThemeNumber()) );
        if (sharedPref.getThemeNumber()==1)
            setTheme(R.style.Purple);
        if (sharedPref.getThemeNumber()==2)
            setTheme(R.style.Teal);
        if (sharedPref.getThemeNumber()==3)
            setTheme(R.style.Red);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    // actions after clicking the sign-in button:
    // first read the inputted username and password,
    // then check the existance of the username and the correctness of the password,
    // if all correct, login the user and jump to the homepage (i.e., the MainActivity)
    public void btn_click_sign_in(View view) {
        EditText editTextUserName = (EditText) findViewById(R.id.editText_username);
        EditText editTextPassWord = (EditText) findViewById(R.id.editText_password);
        String username = editTextUserName.getText().toString();
        String password = editTextPassWord.getText().toString();

        for (Iterator iterator = LoginActivity.userProfileList.iterator(); iterator.hasNext(); ) {
            UserProfile temp = (UserProfile) iterator.next();
            if (temp.getUsername().equals(username)){
                if(temp.getPassword().equals(password)){
                    // all correct, login the user, and update the info stored in the variables in LoginActivity
                    LoginActivity.username = temp.getUsername();
                    LoginActivity.themeNum = temp.getThemeNum();

                    //tell the user that the sign-in is successful
                    Toast.makeText(SignInActivity.this,"Sign-in successfully!",
                            Toast.LENGTH_SHORT).show();
                    //jump to LoginActivity
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(SignInActivity.this,
                            "The password is incorrect, please try again",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(SignInActivity.this,
                        "This username does not exist, please try again or create a new user",
                        Toast.LENGTH_SHORT).show();
            }
        }

    }

}