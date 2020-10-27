package kr.co.lecle.aloxide.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import foundation.icon.icx.Call;
import foundation.icon.icx.IconService;
import foundation.icon.icx.Request;
import foundation.icon.icx.SignedTransaction;
import foundation.icon.icx.Transaction;
import foundation.icon.icx.TransactionBuilder;
import foundation.icon.icx.data.Address;
import foundation.icon.icx.data.Bytes;
import foundation.icon.icx.transport.http.HttpProvider;
import foundation.icon.icx.transport.jsonrpc.RpcItem;
import foundation.icon.icx.transport.jsonrpc.RpcObject;
import foundation.icon.icx.transport.jsonrpc.RpcValue;
import kr.co.lecle.aloxide.model.BlockchainAccount;
import kr.co.lecle.aloxide.old.ICONWalletManager;
import okhttp3.OkHttpClient;

/**
 * Created by quocb14005xx on 12,October,2020
 * "https://bicon.net.solidwallet.io/api/v3"
 */
public class IconNetworkService extends BlockchainNetwork {

    private IconService iconService;
    private String enityName;
    private String contract;
    private BlockchainAccount account;
    private int nId = 3;//testnet =3, mainnet 1

    /**
     * @param enityName need to lower case, ex: Poll -> poll to check the method like cre+`poll`, upd+`poll`...
     * @param account
     * @param contract
     * @param url
     * @param networkId
     */
    public IconNetworkService(String enityName, BlockchainAccount account, String contract, String url, Integer networkId) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(3000, TimeUnit.MILLISECONDS)
                .writeTimeout(6000, TimeUnit.MILLISECONDS)
                .build();
        iconService = new IconService(new HttpProvider(okHttpClient, url));
        this.enityName = enityName;
        this.account = account;
        this.contract = contract;
        if (networkId != null) {
            this.nId = networkId;
        }
    }

    /**
     * Find by id
     *
     * @param id
     * @return RpxItem, Type of Map
     * @throws Exception
     */
    @Override
    public Object get(Object id) throws Exception {
        final String methodName = "get" + this.enityName;
        boolean valid = this.validate();
        if (!valid) throw new Exception("The method name " + methodName + " is not valid!");
        RpcObject params = new RpcObject.Builder()
                .put("id", new RpcValue(id.toString()))
                .build();

        Call<RpcItem> call = new Call.Builder()
                .from(new Address(this.account.getAddress()))
                .to(new Address(this.contract))
                .method(methodName)
                .params(params)
                .build();
        Request<RpcItem> request = iconService.call(call);

        try {
            RpcItem res = request.execute();
            return res;
        } catch (Exception e) {
            return "Not found";
        }
    }

    /**
     * add new record
     *
     * @param params
     * @return txHash
     * @throws Exception
     */
    @Override
    public Object add(HashMap<String, Object> params) throws Exception {
        final String methodName = "cre" + this.enityName;
        boolean valid = this.validate();
        if (!valid) throw new Exception("The method name " + methodName + " is not valid!");
        return sendTransaction(methodName, converter(params));
    }

    RpcObject converter(HashMap<String, Object> data) {
        if (data == null) return null;
        RpcObject.Builder params = new RpcObject.Builder();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            params.put(key, new RpcValue((String) value));
        }

        return params.build();
    }

    /**
     * update at id
     *
     * @param id
     * @param params
     * @return txHash
     * @throws Exception
     */
    @Override
    public Object update(String id, HashMap<String, Object> params) throws Exception {
        final String methodName = "upd" + this.enityName;
        boolean valid = this.validate();
        if (!valid) throw new Exception("The method name " + methodName + " is not valid!");
        return sendTransaction(methodName, converter(params));
    }

    /**
     * delete the record by object id
     *
     * @param id
     * @return txHash
     * @throws Exception
     */
    @Override
    public Object delete(String id) throws Exception {
        final String methodName = "del" + this.enityName;
        boolean valid = this.validate();
        if (!valid) throw new Exception("The method name " + methodName + " is not valid!");
        RpcObject params = new RpcObject.Builder()
                .put("id", new RpcValue(id))
                .build();
        return sendTransaction(methodName, params);
    }

    /**
     * Validation in ICON Network like: check the method name exist or not in SCORE,...
     *
     * @return
     */
    @Override
    public boolean validate() {
        return true;
    }

    /**
     * @param methodName
     * @param params
     * @return txHash
     * @throws IOException
     */
    private Object sendTransaction(String methodName, RpcObject params) throws Exception {
        if (this.account == null)
            throw new Exception("[Aloxide]:::You need setup the private key to get the wallet in ICON network");
        if (this.contract == null)
            throw new Exception("[Aloxide]:::You need setup the contract to send transaction in ICON network");
        Transaction transaction = TransactionBuilder.newBuilder()
                .nid(BigInteger.valueOf(nId))
                .from(new Address(this.account.getAddress()))
                .to(new Address(this.contract))
                .stepLimit(new BigInteger("1000000"))
                .nonce(new BigInteger("1000000"))
                .call(methodName)
                .params(params)
                .build();

        SignedTransaction signedTransaction =
                new SignedTransaction(transaction
                        , ICONWalletManager.getInstance().getWallet(this.account.getPrivateKey()));
        Request<Bytes> request = iconService.sendTransaction(signedTransaction);

        try {
            return request.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
