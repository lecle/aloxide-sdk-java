package kr.co.lecle.aloxide;


/**
 * Created by quocb14005xx on 12,October,2020
 */
public interface AbsAloxideMethod {

    Object get(Object id) throws Exception;

    Object add(Object params) throws Exception;

    Object update(String id, Object params) throws Exception;

    Object delete(String id) throws Exception;



}
