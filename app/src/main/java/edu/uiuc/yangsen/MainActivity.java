package edu.uiuc.yangsen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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