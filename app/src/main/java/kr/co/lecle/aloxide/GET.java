package kr.co.lecle.aloxide;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Properties;

import kr.co.lecle.aloxide.model.BlockchainAccount;

/**
 * Created by quocb14005xx on 22,October,2020
 */
public class GET {
    private static Aloxide handleEosNetwork(String accountName, String privateKey, String url, String entityName, String contract) {
        BlockchainAccount bcAccount = new BlockchainAccount.BlockchainAccountBuilder()
                .setName(accountName)
                .setPrivateKey(privateKey)
                .build();

        return AloxideBuilder.newBuilder()
                .setNetwork(Network.EOS)
                .setUrl(url)
                .setBlockchainAccount(bcAccount)
                .setEnityName(entityName)
                .setContract(contract)
                .build();
    }

    private static Aloxide handleIconNetwork(String address, String pk, String url, String entityName, String contract) {
        BlockchainAccount bcAccount = new BlockchainAccount.BlockchainAccountBuilder()
                .setAddress(address)
                .setPrivateKey(pk)
                .build();

        return AloxideBuilder.newBuilder()
                .setNetwork(Network.ICON)
                .setUrl(url)
                .setBlockchainAccount(bcAccount)
                .setEnityName(entityName)
                .setContract(contract)
                .build();
    }

    public static void main(String[] args) {
        System.out.println("[Aloxide JavaSDK]::::::GET");

        if (args.length == 0) {
            System.out.println("Please provide the Entity name");
            return;
        }
        if (args.length <= 1) {
            System.out.println("Please provide the id you want to get");
            return;
        }
        String id = args[1];
        String entityName = args[0];
        System.out.println("Arguments: id="+id +", entityName="+entityName);

        File file = new File(System.getenv().get("PWD")+"/env.properties");

        if (file.exists()) {
            try {
                Properties properties = new Properties();
                properties.load(new FileInputStream(file));
                String accountName = properties.getProperty("app_blockchain_account");
                String blockchain_contract = properties.getProperty("app_blockchain_contract");
                String pk = properties.getProperty("app_blockchain_account_pk");
                String url = properties.getProperty("app_blockchain_url");
                String blockchainType = properties.getProperty("app_blockchain_type");

                Aloxide aloxide;
                if (blockchainType.contains("eos")) {
                    aloxide = handleEosNetwork(accountName, pk, url, entityName, blockchain_contract);
                } else {
                    aloxide = handleIconNetwork(accountName, pk, url, entityName, blockchain_contract);
                }
                try {

                    Object result = aloxide.get(id);
                    System.out.println("--------------- Your result here ---------------");
                    System.out.println(result);
                    System.out.println("------------------------------------------------");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Configuration file not found.");
        }
    }
}
