package kr.co.lecle.aloxide;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import kr.co.lecle.aloxide.model.Field;
import kr.co.lecle.aloxide.model.FieldDetail;

/**
 * Created by quocb14005xx on 23,October,2020
 */
public class UPDATE {

    public static void main(String[] args) {
        System.out.println("[Aloxide JavaSDK]::::::UPDATE");
        File file = AloxideUtils.getEnvFile();

        if (args.length <= 1) {
            System.out.println("Please provide the required fields");
            return;
        }

        String entityName = args[0];
        HashMap<String, Object> d = new HashMap<>();

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
                if (blockchainType.contains("eos")  ||blockchainType.contains("can")) {
                    aloxide = AloxideUtils.handleEosNetwork(accountName, pk, url, entityName, blockchain_contract);
                } else {
                    aloxide = AloxideUtils.handleIconNetwork(accountName, pk, url, entityName, blockchain_contract);
                }

                try {
                    String method = "upd" + entityName.toLowerCase();
                    List<Field> fields = aloxide.getFields();
                    for (int i = 0; i < fields.size(); i++) {
                        if (fields.get(i).getName().equals(method)) {
                            int sizeOfFields = fields.get(i).getFields().size();
//                            if (blockchainType.contains("eos")) {
//                                sizeOfFields--;
//                            }

                            StringBuilder f = new StringBuilder();

                            for (int j = 0; j < sizeOfFields; j++) {
                                FieldDetail fieldDetail = fields.get(i).getFields().get(j);
                                f.append(fieldDetail.getName()).append(", ");
                            }

                            if (blockchainType.contains("eos") ||blockchainType.contains("can")) {
                                if (sizeOfFields - 1 != args.length - 1) {
                                    System.out.println("Wrong arguments, you passed wrong arguments. Please follow these fields [" + f.toString() + "]");
                                    return;
                                }
                            } else {
                                if (sizeOfFields != args.length - 1) {
                                    System.out.println("Wrong arguments, you passed wrong arguments. Please follow these fields [" + f.toString() + "]");
                                    return;
                                }
                            }
                            for (int j = 0; j < sizeOfFields; j++) {
                                FieldDetail fieldDetail = fields.get(i).getFields().get(j);
                                if (!fieldDetail.getName().equals("user")) {
                                    if (blockchainType.contains("icon")) {
                                        d.put(fieldDetail.getName(), args[j + 1]);
                                    } else {
                                        d.put(fieldDetail.getName(), args[j]);
                                    }

                                }
                            }
                            break;
                        }
                    }


                } catch (Exception e) {
                    System.out.println("Error while map data: " + e.getMessage());
                    return;
                }

                try {
                    Object result = aloxide.update((String) d.get("id"), d);
                    System.out.println("\n\n\n\n\n");
                    System.out.println("--------------- YOUR RESULT HERE ---------------");
                    System.out.println("================================================");
                    System.out.println("Transaction ID: " + result + "\n");
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
