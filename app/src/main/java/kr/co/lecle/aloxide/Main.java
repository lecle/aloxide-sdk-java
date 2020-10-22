package kr.co.lecle.aloxide;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import kr.co.lecle.aloxide.model.BlockchainAccount;

/**
 * Created by quocb14005xx on 07,October,2020
 */
public class Main {

    private static void handleEosNetwork(String accountName, String privateKey, String url, String entityName, String contract) {
        BlockchainAccount bcAccount = new BlockchainAccount.BlockchainAccountBuilder()
                .setName(accountName)
                .setPrivateKey(privateKey)
                .build();

        Aloxide aloxide = AloxideBuilder.newBuilder()
                .setNetwork(Network.EOS)
                .setUrl(url)
                .setBlockchainAccount(bcAccount)
                .setEnityName(entityName)
                .setContract(contract)
                .build();
    }

    private static void handleIconNetwork(String address, String pk, String url, String entityName, String contract) {
        BlockchainAccount bcAccount = new BlockchainAccount.BlockchainAccountBuilder()
                .setAddress(address)
                .setPrivateKey(pk)
                .build();

        Aloxide aloxide = AloxideBuilder.newBuilder()
                .setNetwork(Network.ICON)
                .setUrl(url)
                .setBlockchainAccount(bcAccount)
                .setEnityName(entityName)
                .setContract(contract)
                .build();
    }

    public static void main(String[] args) {
        System.out.println("Aloxide JavaSDK");
        System.out.println("Arguments: " + Arrays.toString(args));
        if (args.length == 0) {
            System.out.println("Please provide the Entity name");
            return;
        }
        String entityName = args[0];

        File file = new File("./env.properties");
        if (file.exists()) {
            try {
                Properties properties = new Properties();
                properties.load(new FileInputStream(file));
                String accountName = properties.getProperty("app_blockchain_account");
                String blockchain_contract = properties.getProperty("app_blockchain_contract");
                String pk = properties.getProperty("app_blockchain_account_pk");
                String url = properties.getProperty("app_blockchain_url");
                String blockchainType = properties.getProperty("app_blockchain_type");

                if (blockchainType.contains("eos")) {
                    handleEosNetwork(accountName, pk, url, entityName, blockchain_contract);
                } else {
                    handleIconNetwork(accountName, pk, url, entityName, blockchain_contract);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Configuration file not found.");
        }
    }
}
