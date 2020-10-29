package kr.co.lecle.aloxide.service;

import java.util.HashMap;
import java.util.List;

import kr.co.lecle.aloxide.model.Field;


/**
 * Created by quocb14005xx on 14,October,2020
 */
public abstract class BlockchainNetwork {
    final String TAG = "BlockchainNetwork";

    public abstract Object get(Object id) throws Exception;
    public abstract List<Field> getFields() throws Exception;

    public abstract Object add(HashMap<String, Object> params) throws Exception;

    public abstract Object update(String id, HashMap<String, Object> params) throws Exception;

    public abstract Object delete(String id) throws Exception;

    public abstract boolean validate();


}
