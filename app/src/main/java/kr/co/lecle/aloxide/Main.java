package kr.co.lecle.aloxide;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.HashMap;
import java.util.Map;

import kr.co.lecle.aloxide.model.BlockchainAccount;

/**
 * Created by quocb14005xx on 07,October,2020
 */
public class Main {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void main(String[] args) {
        System.out.println("Aloxide JavaSDK");

        BlockchainAccount bcAccount = new BlockchainAccount.BlockchainAccountBuilder()
                .setName("aloxidejs123")
                .setPrivateKey("5JHQ3GuzcQtEQgG3SGvtDU7v2b7ioKznYBizA1V5mBUUsLNcXdQ")
                .build();

        Aloxide aloxide = AloxideBuilder.newBuilder()
                .setNetwork(Network.EOS)
                .setUrl("https://testnet.canfoundation.io")
                .setBlockchainAccount(bcAccount)
                .setEnityName("Poll")
                .setContract("aloxidejs123")
                .build();

        try {
            Map<String, String> d = new HashMap<>();
            d.put("id", "9999");
            d.put("user", "aloxidejs123");
            d.put("name", "NEW NAME");
            d.put("body", "NEW BODY");
            aloxide.add(d);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
