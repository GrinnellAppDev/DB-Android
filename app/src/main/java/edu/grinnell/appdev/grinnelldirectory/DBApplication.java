package edu.grinnell.appdev.grinnelldirectory;

import android.app.Application;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class DBApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        if (!EncryptionUtils.isEncryptionKeySet(this)) {
            EncryptionUtils.setAppEncryptionKey(this, "" + System.currentTimeMillis());
        }
    }
}
