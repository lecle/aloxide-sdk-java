package kr.co.lecle.aloxide;

import org.junit.Test;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;

import foundation.icon.icx.data.TransactionResult;

import static org.junit.Assert.assertNotNull;

public class ICONWalletManagerTest {
    @Test
    public void create_wallet() {
        System.out.println("####create_wallet--------------------------");

        try {
            Map<String, String> created = ICONWalletManager.getInstance().createWallet();

            assertNotNull(created);
            String pubKey = created.get("address");
            System.out.println("Address: " + pubKey);
            assertNotNull(pubKey);

            String prvKey = created.get("private_key");
            System.out.println("Private key: " + prvKey);
            assertNotNull(prvKey);


        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void get_wallet_by_prvkey() {
        System.out.println("####get_wallet_by_prvkey--------------------------");
        Map<String, String> loaded = ICONWalletManager.getInstance().getWalletByPrivateKey("3fba1413c4833613481571b1df1981c45ccba716617245fbf70e7513e94d2d19");
        assertNotNull(loaded);
        String pubKey = loaded.get("address");
        System.out.println("Address: " + pubKey);
        assertNotNull(pubKey);

        String prvKey = loaded.get("private_key");
        System.out.println("Private key: " + prvKey);
        assertNotNull(prvKey);

    }

    @Test
    public void send_icx() {
        try {
            TransactionResult result = ICONTransactionManager.getInstance("https://bicon.net.solidwallet.io/api/v3").sendICX("c958108ccc79513ef9bf7647c29194199e3c5b86a88cbbc236dd5f74dfc37366", "1", "hx1f617adb52d49f65d60c48a3109fb0e7b3cd72ea", false);
            System.out.println("TransactionResult " + result.getStatus());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
