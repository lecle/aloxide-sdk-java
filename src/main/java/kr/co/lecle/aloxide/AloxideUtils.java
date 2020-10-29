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

    public static File getEnvFile() {
        return new File(System.getenv().get("PWD") + "/env.properties");
    }

    public static String getTransactionUrl(String transactionId, Network network, String host) {
//        https://local.bloks.io/transaction/dddb927a76a17c33e0fa4050ed4ac7c4245cf77b75ca3e05bcee2bbc00e271f4?nodeUrl=history.testnet.canfoundation.io
//        https://bicon.tracker.solidwallet.io/transaction/0x62c655edc89bc231badc92da5e7c1634b601e5cb0614d0cf84b5878091d62b54
        String output;
        if (network == Network.EOS) {
            output = "https://local.bloks.io/transaction/" + transactionId + "?nodeUrl=history." + host;
        } else {
            output = host + "/transaction/" + transactionId;
        }
        return output;
    }
    static String[] pollColums = new String[]{"id", "name", "body"};
    static String[] voteColums = new String[]{"id", "pollId", "ownerId","point"};
}
