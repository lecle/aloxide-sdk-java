package kr.co.lecle.aloxide.old;


/**
 * Created by quocb14005xx on 07,October,2020
 */

public class EOSTransactionManager {
//    private static EOSTransactionManager instance = null;
//
//    private EOSTransactionManager() {
//    }
//
//    public static EOSTransactionManager getInstance() {
//        if (instance == null) {
//            return new EOSTransactionManager();
//        }
//        return instance;
//    }
//
//
//    void createTransaction() throws EosioJavaRpcProviderInitializerError, SerializationProviderError, TransactionPrepareError, TransactionSignAndBroadCastError {
//        IRPCProvider rpcProvider = new EosioJavaRpcProviderImpl("https://baseurl.com/v1/");
//        ISerializationProvider serializationProvider = new AbiEosSerializationProviderImpl();
//        IABIProvider abiProvider = new ABIProviderImpl(rpcProvider, serializationProvider);
//        SoftKeySignatureProviderImpl signatureProvider = new SoftKeySignatureProviderImpl();
//
//
//        TransactionSession session = new TransactionSession(
//                serializationProvider,
//                rpcProvider,
//                abiProvider,
//                signatureProvider
//        );
//
//        TransactionProcessor processor = session.getTransactionProcessor();
//
//        String jsonData = "{\n" +
//                "\"from\": \"person1\",\n" +
//                "\"to\": \"person2\",\n" +
//                "\"quantity\": \"10.0000 EOS\",\n" +
//                "\"memo\" : \"Something\"\n" +
//                "}";
//
//        List<Authorization> authorizations = new ArrayList<>();
//        authorizations.add(new Authorization("myaccount", "active"));
//        List<Action> actions = new ArrayList<>();
//        actions.add(new Action("eosio.token", "transfer", authorizations, jsonData));
//
//        processor.prepare(actions);
//
//        PushTransactionResponse pushTransactionResponse = processor.signAndBroadcast();
//    }
}
