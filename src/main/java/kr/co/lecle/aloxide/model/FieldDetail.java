package kr.co.lecle.aloxide.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by quocb14005xx on 28,October,2020
 */
public class FieldDetail {

    private String ricardianContract;

    private String name;

    private String type;

    public FieldDetail() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("ricardian_contract")
    public String getRicardianContract() {
        return ricardianContract;
    }

    public void setRicardianContract(String ricardianContract) {
        this.ricardianContract = ricardianContract;
    }
}