package edu.uiuc.try02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    // get the root reference of different variables stored in the Google firebase
    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference userProfileRef = rootRef.child("userProfile");
    ThemeSharedPref sharedPref;

    public static List<UserProfile> userProfileList= new ArrayList<>(); // this variable stores the info of all registered users as an ArrayList
    public static String username; // this variable stores the username of the current user who has logged in
    public static int themeNum = 1; // this variable stores the theme number of the current user who has logged in, 1 is the default theme number




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // code that is run during the onCreate phase
        sharedPref = new ThemeSharedPref(this);
        Log.e("themeeee", "onCreate:"+String.valueOf(sharedPref.getThemeNumber()) );
        if (sharedPref.getThemeNumber()==1)
            setTheme(R.style.Purple);
        if (sharedPref.getThemeNumber()==2)
            setTheme(R.style.Teal);
        if (sharedPref.getThemeNumber()==3)
            setTheme(R.style.Red);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    @Override
    protected void onStart() {
        // code that is run during the onStart phase
        super.onStart();

        // load/refresh the userProfileList which contains all user profiles when database changes
        userProfileRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (DataSnapshot dataSnapshot) {
                //clear the existing UserPofileList
                userProfileList.clear();
                //iterate through all the nodes of userProfile in the database
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //get userPofile
                    UserProfile userProfile = postSnapshot.getValue(UserProfile.class);
//                    //decrypt password
//                    if(userProfile != null){ userProfile.decryptPassword();}
                    //add userProfile to the list
                    userProfileList.add(userProfile);
                }
            }
            @Override
            public void onCancelled (DatabaseError databaseError) {
            }
        });

    }

    public void btn_sign_in(View view) {
        // after clicking, jump to the sign-in page
        Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
        startActivity(intent);
    }

    public void btn_sign_up(View view) {
        // after clicking, jump to the sign-up page
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
    }

}