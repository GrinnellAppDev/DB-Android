package edu.grinnell.appdev.grinnelldirectory;
// package conventions?

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

public class User {

    private final static String SHARED_PREFERENCES = "user";
    private final static String USERNAME = "username";
    private final static String PASSWORD = "password";

    @SerializedName("un")
    private String username;
    @SerializedName("pw")
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public static String getUsername(Context context) {
        return getSharedPreferences(context).getString(USERNAME, null);
    }

    public static String getPassword(Context context) {
        return getSharedPreferences(context).getString(PASSWORD, null);
    }

    public static boolean isLoggedIn(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        String username = preferences.getString(USERNAME, null);
        String password = preferences.getString(PASSWORD, null);
        return username != null && password != null;
    }

    /* Sets new username and password if they are valid */
    public static void saveCredentials(Context context, String username, String password) {
        SharedPreferences preferences = getSharedPreferences(context);
        Editor editor = preferences.edit();
        editor.putString(USERNAME, username);
        editor.putString(PASSWORD, password);
        editor.apply();
    }

    public static void deleteCredentials(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        // same thing as context.getApplicationContext().getSharedPreferences(...)?
        return context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }

    private static void setUsername(Context context, String username) {
        SharedPreferences preferences = getSharedPreferences(context);
        Editor editor = preferences.edit();
        editor.putString(USERNAME, username);
        editor.apply();
    }

    private static void setPassword(Context context, String password) {
        SharedPreferences preferences = getSharedPreferences(context);
        Editor editor = preferences.edit();
        editor.putString(PASSWORD, password);
        editor.apply();
    }
}
