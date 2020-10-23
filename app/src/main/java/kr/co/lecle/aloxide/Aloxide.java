package kr.co.lecle.aloxide;


import java.util.HashMap;

import kr.co.lecle.aloxide.model.BlockchainAccount;
import kr.co.lecle.aloxide.service.BlockchainNetwork;
import kr.co.lecle.aloxide.service.EosNetworkService;
import kr.co.lecle.aloxide.service.IconNetworkService;

/**
 * Created by quocb14005xx on 12,October,2020
 */
enum Network {
    EOS, ICON
}

class Aloxide implements AbsAloxideMethod {

    AloxideData aloxideData;
    BlockchainNetwork blockchainNetwork;

    @Override
    public Object get(Object id) throws Exception {
        return this.blockchainNetwork.get(id);
    }

    @Override
    public Object add(HashMap<String, Object> params) throws Exception {
        return this.blockchainNetwork.add(params);
    }

    @Override
    public Object update(String id, HashMap<String, Object> params) throws Exception {
        return this.blockchainNetwork.update(id, params);
    }

    @Override
    public Object delete(String id) throws Exception {
        return this.blockchainNetwork.delete(id);
    }
}


/**
 * The data in the chain of builder
 */
class AloxideData {
    Network network;
    BlockchainAccount blockchainAccount;
    String contract;
    String enityName;
    String url;

    /**
     * nId: main net = 1, testnet = 3 (use for ICON network)
     */
    int nId = 3;

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public BlockchainAccount getBlockchainAccount() {
        return blockchainAccount;
    }

    public void setBlockchainAccount(BlockchainAccount blockchainAccount) {
        this.blockchainAccount = blockchainAccount;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getEnityName() {
        return enityName;
    }

    public void setEnityName(String enityName) {
        this.enityName = enityName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getnId() {
        return nId;
    }

    public void setnId(int nId) {
        this.nId = nId;
    }
}

/**
 * The first builder use to trigger for setting the Blockchain network first
 */
class AloxideBuilder {

    private AloxideData aloxideData;

    private AloxideBuilder() {
        this.aloxideData = new AloxideData();
    }

    static AloxideBuilder newBuilder() {
        return new AloxideBuilder();
    }

    AloxideNetworkBuilder setNetwork(Network network) {
        aloxideData.setNetwork(network);
        return new AloxideNetworkBuilder(aloxideData);
    }

}

/**
 * The last Builder use to construct the Aloxide
 */
class AloxideNetworkBuilder {
    private final AloxideData aloxideData;

    AloxideNetworkBuilder(AloxideData aloxideData) {
        this.aloxideData = aloxideData;
    }

    AloxideNetworkBuilder setBlockchainAccount(BlockchainAccount blockchainAccount) {
        aloxideData.setBlockchainAccount(blockchainAccount);
        return new AloxideNetworkBuilder(aloxideData);
    }

    AloxideNetworkBuilder setContract(String contract) {
        aloxideData.setContract(contract);
        return new AloxideNetworkBuilder(aloxideData);
    }

    AloxideNetworkBuilder setEnityName(String enityName) {
        aloxideData.setEnityName(enityName.toLowerCase());
        return new AloxideNetworkBuilder(aloxideData);
    }

    AloxideNetworkBuilder setUrl(String url) {
        aloxideData.setUrl(url);
        return new AloxideNetworkBuilder(aloxideData);
    }

    AloxideNetworkBuilder setNid(int nid) {
        aloxideData.setnId(nid);
        return new AloxideNetworkBuilder(aloxideData);
    }

    Aloxide build() {
        Aloxide aloxide = new Aloxide();
        aloxide.aloxideData = this.aloxideData;
        switch (aloxideData.network) {
            case EOS:
                aloxide.blockchainNetwork = new EosNetworkService(aloxideData.enityName
                        , aloxideData.blockchainAccount
                        , aloxideData.contract
                        , aloxideData.url);
                return aloxide;

            case ICON:
                aloxide.blockchainNetwork = new IconNetworkService(
                        aloxideData.enityName
                        , aloxideData.blockchainAccount
                        , aloxideData.contract
                        , aloxideData.url
                        , aloxideData.nId);
                return aloxide;

            default: {
                return null;

            }
        }
    }

    /**
     * Note
     *    * ICON
     *      * // new BlockchainAccount.BlockchainAccountBuilder()
     *      * //         .setAddress("hxe7af5fcfd8dfc67530a01a0e403882687528dfcb")
     *      * //         .setPrivateKey("592eb276d534e2c41a2d9356c0ab262dc233d87e4dd71ce705ec130a8d27ff0c")
     *      * //         .build()
     *      * <p>
     *      * // cx26d2757d45ea7e559940d86761330005b0e9f2d8
     *      * <p>
     *      * EOS
     *      * //  new BlockchainAccount.BlockchainAccountBuilder()
     *      * //          .setName("aloxidejs123")
     *      * //          .build()
     *      * // contract: "aloxidejs123"
     */
}
