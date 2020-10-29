package kr.co.lecle.aloxide.model;


import java.util.List;

/**
 * Created by quocb14005xx on 28,October,2020
 */
public class Field {

    private String name;

    private String base;

    private List<FieldDetail> fields;

    public Field() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public List<FieldDetail> getFields() {
        return fields;
    }

    public void setFields(List<FieldDetail> fields) {
        this.fields = fields;
    }


}


