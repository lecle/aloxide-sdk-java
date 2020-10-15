package kr.co.lecle.aloxide;

import android.os.Build;

import androidx.annotation.RequiresApi;

/**
 * Created by quocb14005xx on 12,October,2020
 */
enum Network {
    EOS, ICON
}

class Aloxide implements AbsAloxideMethod {

    private Network currentNetwork;

    private IconNetworkService iconNetworkService;

    private EosNetworkService eosNetworkService;

    Aloxide(Network network) {
        this.currentNetwork = network;
    }

    /**
     * @param entityName
     * @param account
     * @param contract
     * @return ex:
     * ICON
     * // new BlockchainAccount.BlockchainAccountBuilder()
     * //         .setAddress("hxe7af5fcfd8dfc67530a01a0e403882687528dfcb")
     * //         .setPrivateKey("592eb276d534e2c41a2d9356c0ab262dc233d87e4dd71ce705ec130a8d27ff0c")
     * //         .build()
     * <p>
     * // cx26d2757d45ea7e559940d86761330005b0e9f2d8
     * <p>
     * EOS
     * //  new BlockchainAccount.BlockchainAccountBuilder()
     * //          .setName("aloxidejs123")
     * //          .build()
     * // contract: "aloxidejs123"
     */
    Aloxide model(String entityName, BlockchainAccount account, String contract) {
        this.iconNetworkService = new IconNetworkService(
                entityName
                , account
                , contract
                , "https://bicon.net.solidwallet.io/api/v3"
                , 3);


        this.eosNetworkService = new EosNetworkService(entityName
                , account
                , contract
                , "https://testnet.canfoundation.io");
        return this;
    }


    @Override
    public Object get(Object id) throws Exception {
        if (currentNetwork == Network.EOS) {
            return eosNetworkService.get(id);
        } else {
            return iconNetworkService.get(id);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Object add(Object params) throws Exception {
        if (currentNetwork == Network.EOS) {
            return eosNetworkService.add(params);
        } else {
            return iconNetworkService.add(params);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Object update(String id, Object params) throws Exception {
        if (currentNetwork == Network.EOS) {
            return eosNetworkService.update(id, params);
        } else {
            return iconNetworkService.update(id, params);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Object delete(String id) throws Exception {
        if (currentNetwork == Network.EOS) {
            return eosNetworkService.delete(id);
        } else {
            return iconNetworkService.delete(id);
        }
    }
}


//public class Aloxide implements AbsAloxideMethod {
//
//    private Network currentNetwork;
//    private IconNetworkService iconNetworkService;
//    private EosNetworkService eosNetworkService;
//
//    public Network getNetwork() {
//        return currentNetwork;
//    }
//
//    private Aloxide(AloxideBuilder builder) {
//        this.currentNetwork = builder.currentNetwork;
//        iconNetworkService = new IconNetworkService(
//                "poll"
//                , new BlockchainAccount("", "592eb276d534e2c41a2d9356c0ab262dc233d87e4dd71ce705ec130a8d27ff0c")
//                , "cx26d2757d45ea7e559940d86761330005b0e9f2d8"
//                , "https://bicon.net.solidwallet.io/api/v3"
//                , 3);
//        eosNetworkService = new EosNetworkService();
//    }
//
//    @Override
//    public Object get(Object id) throws Exception {
//        if (currentNetwork == Network.EOS) {
//            return eosNetworkService.get(id);
//        } else {
//            return iconNetworkService.get(id);
//        }
//    }
//
//    @Override
//    public Object add(Object params) throws Exception {
//        if (currentNetwork == Network.EOS) {
//            return eosNetworkService.add(params);
//        } else {
//            return iconNetworkService.add(params);
//        }
//    }
//
//    @Override
//    public Object update(String id, Object params) throws Exception {
//        if (currentNetwork == Network.EOS) {
//            return eosNetworkService.update(id, params);
//        } else {
//            return iconNetworkService.update(id, params);
//        }
//    }
//
//    @Override
//    public Object delete(String id) throws Exception {
//        return iconNetworkService.delete(id);
//    }
//
//    //Builder Class
//    public static class AloxideBuilder {
//
//        private Network currentNetwork;
//
//        public AloxideBuilder() {
//        }
//
//        public AloxideBuilder setNetwork(Network network) {
//            this.currentNetwork = network;
//            return this;
//        }
//
//        public Aloxide build() {
//            return new Aloxide(this);
//        }
//
//    }
//}