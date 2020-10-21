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

//
//        BlockchainAccount bcAccount = new BlockchainAccount.BlockchainAccountBuilder()
//                .setPrivateKey("592eb276d534e2c41a2d9356c0ab262dc233d87e4dd71ce705ec130a8d27ff0c")
//                .setAddress("hxe7af5fcfd8dfc67530a01a0e403882687528dfcb")
//                .build();
//
//        Aloxide aloxide = AloxideBuilder.newBuilder()
//                .setNetwork(Network.ICON)
//                .setUrl("https://bicon.net.solidwallet.io/api/v3")
//                .setBlockchainAccount(bcAccount)
//                .setEnityName("Poll")
//                .setContract("cx26d2757d45ea7e559940d86761330005b0e9f2d8")
//                .build();

        try {

            Map<String, String> d = new HashMap<>();
            d.put("id", "1111");
            d.put("name", "NEW 1111");
            d.put("body", "NEW 1111");
            Object result = aloxide.add(d);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
