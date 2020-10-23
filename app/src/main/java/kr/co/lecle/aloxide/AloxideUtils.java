package kr.co.lecle.aloxide;

import java.io.File;

import kr.co.lecle.aloxide.model.BlockchainAccount;

/**
 * Created by quocb14005xx on 23,October,2020
 */
public class AloxideUtils {
    public static Aloxide handleEosNetwork(String accountName, String privateKey, String url, String entityName, String contract) {
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

    public static Aloxide handleIconNetwork(String address, String pk, String url, String entityName, String contract) {
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
    public static File getEnvFile(){
        return new File(System.getenv().get("PWD")+"/env.properties");
    }
}
