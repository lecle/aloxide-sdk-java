package kr.co.lecle.aloxide;


import java.util.ArrayList;
import java.util.List;

import one.block.eosiojava.error.serializationProvider.SerializationProviderError;
import one.block.eosiojava.error.session.TransactionPrepareError;
import one.block.eosiojava.error.session.TransactionSignAndBroadCastError;
import one.block.eosiojava.implementations.ABIProviderImpl;
import one.block.eosiojava.interfaces.IABIProvider;
import one.block.eosiojava.interfaces.IRPCProvider;
import one.block.eosiojava.interfaces.ISerializationProvider;
import one.block.eosiojava.models.rpcProvider.Action;
import one.block.eosiojava.models.rpcProvider.Authorization;
import one.block.eosiojava.models.rpcProvider.response.PushTransactionResponse;
import one.block.eosiojava.session.TransactionProcessor;
import one.block.eosiojava.session.TransactionSession;
import one.block.eosiojavaabieosserializationprovider.AbiEosSerializationProviderImpl;
import one.block.eosiojavarpcprovider.error.EosioJavaRpcProviderInitializerError;
import one.block.eosiojavarpcprovider.implementations.EosioJavaRpcProviderImpl;
import one.block.eosiosoftkeysignatureprovider.SoftKeySignatureProviderImpl;

/**
 * Created by quocb14005xx on 07,October,2020
 */

public class EOSTransactionManager {
    private static EOSTransactionManager instance = null;

    private EOSTransactionManager() {
    }

    public static EOSTransactionManager getInstance() {
        if (instance == null) {
            return new EOSTransactionManager();
        }
        return instance;
    }


    void createTransaction() throws EosioJavaRpcProviderInitializerError, SerializationProviderError, TransactionPrepareError, TransactionSignAndBroadCastError {
        IRPCProvider rpcProvider = new EosioJavaRpcProviderImpl("https://baseurl.com/v1/");
        ISerializationProvider serializationProvider = new AbiEosSerializationProviderImpl();
        IABIProvider abiProvider = new ABIProviderImpl(rpcProvider, serializationProvider);
        SoftKeySignatureProviderImpl signatureProvider = new SoftKeySignatureProviderImpl();


        TransactionSession session = new TransactionSession(
                serializationProvider,
                rpcProvider,
                abiProvider,
                signatureProvider
        );

        TransactionProcessor processor = session.getTransactionProcessor();

        String jsonData = "{\n" +
                "\"from\": \"person1\",\n" +
                "\"to\": \"person2\",\n" +
                "\"quantity\": \"10.0000 EOS\",\n" +
                "\"memo\" : \"Something\"\n" +
                "}";

        List<Authorization> authorizations = new ArrayList<>();
        authorizations.add(new Authorization("myaccount", "active"));
        List<Action> actions = new ArrayList<>();
        actions.add(new Action("eosio.token", "transfer", authorizations, jsonData));

        processor.prepare(actions);

        PushTransactionResponse pushTransactionResponse = processor.signAndBroadcast();
    }
}
