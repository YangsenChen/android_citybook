package edu.uiuc.try02;

import java.util.ArrayList;

public class UserProfile {

    private String username;
    private String password;
    private int themeNum;

    // constructor of this class without argument
    public UserProfile () {
    }

    // constructor of this class
    public UserProfile (String userName, String password, int themeNum) {
        this.username = userName;
        this.password = password;
        this.themeNum = themeNum;
    }

    // method for getting the user name
    public String getUsername() {
        return username;
    }

    // method for getting the password
    public String getPassword() {
        return password;
    }

    // method for getting the theme number of the theme preferred by the user
    public int getThemeNum() {
        return themeNum;
    }

}
