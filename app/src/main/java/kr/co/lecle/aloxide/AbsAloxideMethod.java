package kr.co.lecle.aloxide;


import java.util.HashMap;
import java.util.List;

import kr.co.lecle.aloxide.model.Field;

/**
 * Created by quocb14005xx on 12,October,2020
 */
public interface AbsAloxideMethod {

    Object get(Object id) throws Exception;

    Object add(HashMap<String, Object> params) throws Exception;

    Object update(String id, HashMap<String, Object> params) throws Exception;

    Object delete(String id) throws Exception;
    List<Field> getFields() throws Exception;


}
