package kr.co.lecle.aloxide;

/**
 * Created by quocb14005xx on 14,October,2020
 */
abstract class BlockchainNetwork {
    final String TAG = "BlockchainNetwork";

    abstract Object get(Object id) throws Exception;

    abstract Object add(Object params) throws Exception;

    abstract Object update(String id, Object params) throws Exception;

    abstract Object delete(String id) throws Exception;

    boolean validate(String enityName) {
        return true;
    }
}
