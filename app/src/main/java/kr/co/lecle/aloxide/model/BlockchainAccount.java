package kr.co.lecle.aloxide.model;

/**
 * Created by quocb14005xx on 14,October,2020
 */

public class BlockchainAccount {

    private String name;
    private String address;
    private String privateKey;


    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    private BlockchainAccount(BlockchainAccountBuilder builder) {
        this.address = builder.address;
        this.name = builder.name;
        this.privateKey = builder.privateKey;
    }

    public static class BlockchainAccountBuilder {

        private String name = "";
        private String address = "";
        private String privateKey = "";

        public BlockchainAccountBuilder() {
        }

        public BlockchainAccountBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public BlockchainAccountBuilder setAddress(String address) {
            this.address = address;
            return this;

        }

        public BlockchainAccountBuilder setPrivateKey(String privateKey) {
            this.privateKey = privateKey;
            return this;
        }

        public BlockchainAccount build() {
            return new BlockchainAccount(this);
        }

    }
}