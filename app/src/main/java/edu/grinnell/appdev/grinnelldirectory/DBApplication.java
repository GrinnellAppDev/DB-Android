package edu.grinnell.appdev.grinnelldirectory;

import android.app.Application;

public class DBApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (!EncryptionUtils.isEncryptionKeySet(this)) {
            EncryptionUtils.setAppEncryptionKey(this, "" + System.currentTimeMillis());
        }
    }
}
