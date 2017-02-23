package edu.grinnell.appdev.grinnelldirectory;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

/**
 * The User class represents the user of the app.
 * It handles storage of username and password in shared preferences.
 */

public class User {

    private final static String SHARED_PREFERENCES = "user";
    private final static String USERNAME = "username";
    private final static String PASSWORD = "password";

    @SerializedName("un")
    private final String username;

    @SerializedName("pw")
    private final String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
    * Get a User object with username and password from shared preferences
    * @param context context of the activity that calls this method
    * @return User object with saved username and password
    */
    public static User getUser(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        String username = preferences.getString(USERNAME, null);
        String password = preferences.getString(PASSWORD, null);
        return new User(username, password);
    }

    /**
     * Determine whether a user is currently logged in
     * @param context context of the activity that calls this method
     * @return whether a user is logged in
     */
    public static boolean isLoggedIn(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        String username = preferences.getString(USERNAME, null);
        String password = preferences.getString(PASSWORD, null);
        return username != null && password != null;
    }

    /**
     * Save the username and password in shared preferences
     * @param context context of the activity that calls this method
     * @param username the user's new username
     * @param password the user's new password
     */
    public static void saveCredentials(Context context, String username, String password) {
        SharedPreferences preferences = getSharedPreferences(context);
        Editor editor = preferences.edit();
        editor.putString(USERNAME, username);
        editor.putString(PASSWORD, password);
        editor.apply();
    }

    /**
     * Remove the current username and password from shared preferences
     * @param context context of the activity that calls this method
     */
    public static void deleteCredentials(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    /**
     * Get the shared preferences object
     * @param context context of the activity that calls this method
     * @return the shared preferences object
     */
    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }
}
