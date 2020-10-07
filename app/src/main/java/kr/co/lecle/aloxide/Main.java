package kr.co.lecle.aloxide;

import java.io.IOException;

import foundation.icon.icx.data.TransactionResult;

/**
 * Created by quocb14005xx on 07,October,2020
 */
public class Main {
    public static void main(String[] args) {
        if (args != null && args.length > 0) {
            System.out.println("Your private key: " + args[0]);
            System.out.println("Address to send: " + args[1]);
            System.out.println("ICX: " + args[2]);
            try {
                TransactionResult result = ICONTransactionManager.getInstance("https://bicon.net.solidwallet.io/api/v3").sendICX(args[0], args[2].toString(), args[1], false);
                System.out.println("TransactionResult " + result.getStatus());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Please provide the args for me [your private key], [address to send icx], [icx value]");
        }
    }
}
