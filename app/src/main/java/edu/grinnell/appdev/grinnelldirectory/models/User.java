
package edu.grinnell.appdev.grinnelldirectory.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * The User class represents the user of the app.
 * It handles storage of username and password in shared preferences.
 * User objects are immutable. They will not change when the user's credentials are changed using
 * <code>saveCredentials</code> or <code>deleteCredentials</code>.
 */

public class User {

    private final static String PREF_USER = "PREF_USER";
    private final static String USERNAME = "username";
    private final static String PASSWORD = "password";
    private final static String PERSON = "person";

    @SerializedName("un")
    private final String mUsername;

    @SerializedName("pw")
    private final String mPassword;

    public User(String username, String password) {
        mUsername = username;
        mPassword = password;
    }

    /**
     * Getter for mUsername
     *
     * @return mUsername
     */
    public String getUsername() {
        return mUsername;
    }

    /**
     * Get a User object with username and password from shared preferences
     *
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
     *
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
     *
     * @param context  context of the activity that calls this method
     * @param username the user's new username
     * @param password the user's new password
     */
    public static void saveCredentials(Context context, String username, String password) {
        if (username == null || password == null) {
            return;
        }
        SharedPreferences preferences = getSharedPreferences(context);
        Editor editor = preferences.edit();
        editor.putString(USERNAME, username);
        editor.putString(PASSWORD, password);
        editor.apply();
    }

    /**
     * Save the user details of the logged in user in shared preferences
     *
     * @param context context of the activity that calls this method
     * @param person model of logged in user
     */
    public static void saveUserDetails(Context context, Person person) {
        if (context == null || person == null) {
            return;
        }
        Gson gson = new Gson();
        String personJson = gson.toJson(person);
        SharedPreferences preferences = getSharedPreferences(context);
        Editor editor = preferences.edit();
        editor.putString(PERSON, personJson);
    }

    /**
     * Remove the current username and password from shared preferences
     *
     * @param context context of the activity that calls this method
     */
    public static void deleteCredentials(Context context) {
        if (context == null) {
            return;
        }
        SharedPreferences preferences = getSharedPreferences(context);
        Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    /**
     * Get the shared preferences object
     *
     * @param context context of the activity that calls this method
     * @return the shared preferences object
     */
    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREF_USER, Context.MODE_PRIVATE);
    }
}
