package edu.uiuc.try02;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class ThemeSharedPref {

    SharedPreferences mySharedPref ;
    ThemeSharedPref(Context context) {
        mySharedPref = context.getSharedPreferences("AppData",Context.MODE_PRIVATE);
    }

    //To save the night mode state as true or false.
    public void setThemeState(int themeNum) {
        Log.e("themeNum",String.valueOf(themeNum));
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putInt("ThemeNum",themeNum);
        editor.apply();
    }

    //To load the night or day mode.
    public int getThemeNumber (){
        return  mySharedPref.getInt("ThemeNum", -1);
    }
}