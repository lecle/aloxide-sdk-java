package kr.co.lecle.aloxide;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by quocb14005xx on 23,October,2020
 */
public class DELETE {
    public static void main(String[] args) {
        System.out.println("[Aloxide JavaSDK]::::::DELETE");


        if (args.length <= 1) {
            System.out.println("Please provide the required field the Entity name, ID. Follow this statement: gradle DELETE --args=\"entity_name ID\"");

            return;
        }

        String id = args[1];
        String entityName = args[0];

        // System.out.println("Arguments: id=" + id + ", entityName=" + entityName);

        File file = AloxideUtils.getEnvFile();

        if (file.exists()) {
            try {
                Properties properties = new Properties();
                properties.load(new FileInputStream(file));
                String accountName = properties.getProperty("app_blockchain_account");
                String blockchain_contract = properties.getProperty("app_blockchain_contract");
                String pk = properties.getProperty("app_blockchain_account_pk");
                String url = properties.getProperty("app_blockchain_url");
                String blockchainType = properties.getProperty("app_blockchain_type");
                String host = properties.getProperty("app_blockchain_host");

                Aloxide aloxide;
                if (blockchainType.contains("eos") || blockchainType.contains("can")) {
                    aloxide = AloxideUtils.handleEosNetwork(accountName, pk, url, entityName, blockchain_contract);
                } else {
                    aloxide = AloxideUtils.handleIconNetwork(accountName, pk, url, entityName, blockchain_contract);
                }
                try {

                    Object result = aloxide.delete(id);
                    System.out.println("\n\n\n\n\n");
                    System.out.println("--------------- YOUR RESULT HERE ---------------");
                    System.out.println("================================================");
                    System.out.println("Transaction ID: " +result+"\n");
                    System.out.println("Verify information: " + AloxideUtils.getTransactionUrl(result.toString(), aloxide.aloxideData.network, host));
                    System.out.println("================================================");
                    System.out.println("------------------------------------------------");
                    System.out.println("\n\n\n\n\n");

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
