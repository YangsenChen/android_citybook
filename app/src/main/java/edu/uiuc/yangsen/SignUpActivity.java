package edu.uiuc.yangsen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Iterator;

import android.widget.RadioButton;


public class SignUpActivity extends AppCompatActivity {

    private String username;
    private String password;
    private int themeNum = -100; // set a default value to determine if a preferred theme has been selected

    // an auxiliary method for checking if the integer represented by a string is an integer
    public boolean isInteger(String str) {
        for (int i = str.length(); --i >= 0; ) {
            char c = str.charAt(i);
            if (!(Character.isDigit(c))) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }


    // check the validity of the username and password,
    // return false if the username or password does not meet the validity requirements.
    // if the username or password meets all validity requirements,
    // add the information into the database and return true
    public boolean addToDatabase() {
        // check that the username cannot be "admin"
        if (username.equals("admin")) {
            Toast.makeText(SignUpActivity.this,
                    "Signing up failed, username cannot be \"admin\", please re-enter and try again",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        // check that if the inputted user name already exists
        for (Iterator iterator = LoginActivity.userProfileList.iterator(); iterator.hasNext(); ) {
            UserProfile temp2 = (UserProfile) iterator.next();
            if (temp2.getUsername().equals(username)) {
                Toast.makeText(SignUpActivity.this,
                        "Signing up failed, this user name already exists, please re-enter and try again",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        // check the validity of the username, password and theme
        if (username.equals("") || password.equals("")) {
            Toast.makeText(SignUpActivity.this,
                    "Signing up failed, all fields are mandatory",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (themeNum == -100) {
            // check that if the user has selected a preferred theme
            Toast.makeText(SignUpActivity.this,
                    "Signing up failed, please select a preferred theme and then try again",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (isInteger(password.replace(" ", ""))) {
            Toast.makeText(SignUpActivity.this,
                    "Invalid input, the password cannot be composed of pure numbers", Toast.LENGTH_SHORT).show();
            return false;
        } else if ((password.replace(" ", "")).length() < 4) {
            Toast.makeText(SignUpActivity.this,
                    "Invalid input, the password must be composed of at least 4 characters", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            // check the validity of the password
            String str = password;
            if (str.contains("*")
                    || str.contains(",")
                    || str.contains(" ")
                    || str.contains("$")
                    || str.contains("#")
                    || str.contains("%")
                    || str.contains("~")
                    || str.contains("&")
                    || str.contains("|")
                    || str.contains(":")
                    || str.contains("^")
                    || str.contains("`")) {
                Toast.makeText(SignUpActivity.this,
                        "Invalid input, some special characters in the password you input is not allowed",
                        Toast.LENGTH_SHORT).show();
                return false;
            }

            // check the validity of the username
            str = username;
            if (str.contains("*")
                    || str.contains(",")
                    || str.contains(" ")
                    || str.contains("$")
                    || str.contains("#")
                    || str.contains("%")
                    || str.contains("~")
                    || str.contains("&")
                    || str.contains("|")
                    || str.contains(":")
                    || str.contains("^")
                    || str.contains("`")) {
                Toast.makeText(SignUpActivity.this,
                        "Invalid input, some special characters in the username you input is not allowed",
                        Toast.LENGTH_SHORT).show();
                return false;
            } else {
                // get the root reference of the userProfile data stored in the Google firebase
                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference userProfileRef = rootRef.child("userProfile");

                // save the the userProfile instance of the user named "userName" to the database
                UserProfile userProfile = new UserProfile(username, password, themeNum);
//                // encrypt the password
//                userProfile.encryptPassword();
                userProfileRef.child(username).setValue(userProfile);
                return true;
            }
        }
    }

    // actions after clicking the sign-up button:
    // first read the inputted username, password and the selected theme,
    // then check the validity of the username and password,
    // after that, add the information into the database
    public void btn_click_sign_up(View view) {
        EditText editTextUserName= (EditText) findViewById(R.id.editText_hw_username);
        username = editTextUserName.getText().toString().trim();

        EditText editTextPassword= (EditText) findViewById(R.id.editText_hw_password);
        password = editTextPassword.getText().toString().trim();

        RadioButton radioButton_purple = (RadioButton) findViewById(R.id.theme_selection_purple);
        RadioButton radioButton_teal = (RadioButton) findViewById(R.id.theme_selection_teal);
        RadioButton radioButton_red = (RadioButton) findViewById(R.id.theme_selection_red);
        if(radioButton_purple.isChecked()){
            themeNum = 1;
        }else if(radioButton_teal.isChecked()){
            themeNum = 2;
        }else if(radioButton_red.isChecked()){
            themeNum = 3;
        }

        // if add info successfully, jump to LoginActivity
        if(addToDatabase()){
            //tell the user that the sign up is successful
            Toast.makeText(SignUpActivity.this,"New user signed up successfully.",
                    Toast.LENGTH_SHORT).show();
            //jump to LoginActivity
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
//        else{
//            //tell user sign up failed and input valid information again
//            Toast.makeText(SignUpActivity.this,"Signing up failed, please input valid information",
//                    Toast.LENGTH_SHORT).show();
//        }
    }
}