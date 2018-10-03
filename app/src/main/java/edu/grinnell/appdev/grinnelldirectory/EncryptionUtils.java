package edu.grinnell.appdev.grinnelldirectory;

import android.content.Context;
import android.content.SharedPreferences;

import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

@Deprecated
public class EncryptionUtils {

    private static final String PREF_ENCRYPTION = "pref_encryption";
    private static final String ENC_KEY = "enc_key";

    public static String encrypt(Context context, String value) throws GeneralSecurityException {
        return AESCrypt.encrypt(getAppEncryptionKey(context), value);
    }

    public static String decrypt(Context context, String encryptedValue) throws GeneralSecurityException {
        return AESCrypt.decrypt(getAppEncryptionKey(context), encryptedValue);
    }

    static void setAppEncryptionKey(Context context, String key) {
        SharedPreferences preferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(ENC_KEY, key);
        editor.apply();
    }

    static boolean isEncryptionKeySet(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.contains(ENC_KEY);
    }

    private static String getAppEncryptionKey(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.getString(ENC_KEY, "");
    }

    /**
     * Get the shared preferences object
     *
     * @param context context of the activity that calls this method
     * @return the shared preferences object
     */
    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREF_ENCRYPTION, Context.MODE_PRIVATE);
    }
}
