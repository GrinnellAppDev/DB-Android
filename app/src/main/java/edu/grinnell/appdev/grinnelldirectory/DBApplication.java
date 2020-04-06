package edu.grinnell.appdev.grinnelldirectory;

import android.app.Application;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

import static edu.grinnell.appdev.grinnelldirectory.EncryptionUtils.isEncryptionKeySet;
import static edu.grinnell.appdev.grinnelldirectory.EncryptionUtils.setAppEncryptionKey;

public class DBApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        if (!isEncryptionKeySet(this)) {
            setAppEncryptionKey(this, "" + System.currentTimeMillis());
        }
    }
}
