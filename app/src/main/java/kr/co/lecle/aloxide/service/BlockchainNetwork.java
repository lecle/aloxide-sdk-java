package kr.co.lecle.aloxide.service;

/**
 * Created by quocb14005xx on 14,October,2020
 */
public abstract class BlockchainNetwork {
    final String TAG = "BlockchainNetwork";

    public abstract Object get(Object id) throws Exception;

    public abstract Object add(Object params) throws Exception;

    public abstract Object update(String id, Object params) throws Exception;

    public abstract Object delete(String id) throws Exception;

    public abstract boolean validate();


}
