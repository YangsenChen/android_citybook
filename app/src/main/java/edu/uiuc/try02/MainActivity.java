package edu.uiuc.try02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_main);
        setTitle("Team 27-" + LoginActivity.username);

    }

    public void btn_click_sign_out(View view) {
        // after clicking, log out the user and jump to the landing page
        LoginActivity.username = "";
        LoginActivity.themeNum = 1; // reset the current theme number to 1

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

}