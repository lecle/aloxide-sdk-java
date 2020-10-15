package kr.co.lecle.aloxide;

import android.content.Context;
import android.os.Environment;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;

import foundation.icon.icx.KeyWallet;
import foundation.icon.icx.crypto.KeystoreException;
import kr.co.lecle.aloxide.old.ICONWalletManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("ke.co.lecle.aloxide", appContext.getPackageName());

        //create wallet
        Map<String, String> created = null;
        try {
            created = ICONWalletManager.getInstance().createWallet();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }

        assertNotNull(created);

        // get wallet first time
        Map<String, String> loadFirstTime =
                ICONWalletManager.getInstance().getWalletByPrivateKey(created.get("private_key"));
        KeyWallet loadFirstTimeAndReturnWallet =
                ICONWalletManager.getInstance().getWallet(created.get("private_key"));
        assertNotNull(loadFirstTime);
        assertNotNull(loadFirstTimeAndReturnWallet);
        assertEquals(created.get("private_key"), loadFirstTime.get("private_key"));
        assertEquals(created.get("address"), loadFirstTime.get("address"));

        // store wallet to file

        File f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "iconex");
//        if (!f.exists()) {
//            boolean mkdir = f.mkdirs();
//            assertTrue("Folder '" + f.getAbsolutePath() + "' not Present!", mkdir);
//        }
//        boolean delete = f.delete();
//        assertTrue("Folder '" + f.getAbsolutePath() + "' is Present!", delete);

        String storedPath = null;
        try {
            storedPath = ICONWalletManager.getInstance().storeTheWallet(f.getPath(), "hbqhbq", loadFirstTimeAndReturnWallet);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeystoreException e) {
            e.printStackTrace();
        }

        assertNotNull(storedPath);


        // load key wallet from file
        try {
            Map<String,String> loadFromFile =
                    ICONWalletManager.getInstance().getStoredWallet("hbqhbq", storedPath);

            assertNotNull(loadFromFile);
            String pubKey = loadFromFile.get("address");
            System.out.println("Address: " + pubKey);
            assertNotNull(pubKey);

            String prvKey = loadFromFile.get("private_key");
            System.out.println("Private key: " + prvKey);
            assertNotNull(prvKey);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeystoreException e) {
            e.printStackTrace();
        }
    }


}
