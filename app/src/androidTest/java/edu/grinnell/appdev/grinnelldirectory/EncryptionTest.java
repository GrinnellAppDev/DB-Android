package edu.grinnell.appdev.grinnelldirectory;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static edu.grinnell.appdev.grinnelldirectory.EncryptionUtils.decrypt;
import static edu.grinnell.appdev.grinnelldirectory.EncryptionUtils.encrypt;
import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class EncryptionTest {

    @Test
    public void testEncryption() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        String encrypted = encrypt(appContext, "String to Encrypt");
        String decrypted = decrypt(appContext, encrypted);
        assertEquals("String to Encrypt", decrypted);


    }

}
