package kr.co.lecle.aloxide;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by quocb14005xx on 07,October,2020
 */
public class Main {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void main(String[] args) {
        System.out.println("Aloxide JavaSDK");
        Aloxide poll = new Aloxide(Network.EOS).model("Poll"
                , new BlockchainAccount.BlockchainAccountBuilder()
                        .setName("aloxidejs123")
                        .setPrivateKey("5JHQ3GuzcQtEQgG3SGvtDU7v2b7ioKznYBizA1V5mBUUsLNcXdQ")
                        .build()
                , "aloxidejs123");

        try {
            Map<String, String> d = new HashMap<>();
            d.put("id", "999");
            d.put("user", "aloxidejs123");
            d.put("name", "NEW NAME");
            d.put("body", "NEW BODY");
            poll.add(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
