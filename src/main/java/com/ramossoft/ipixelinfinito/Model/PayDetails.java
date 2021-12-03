package com.ramossoft.ipixelinfinito.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

// this POJO class for the response of the objects that's used to map the response
public class PayDetails implements Serializable {
    @Expose
    @SerializedName("payId")// serializable is used to specify the name of JSON on the API
    private String payId;

    @Expose
    @SerializedName("version")
    private String version;


    public List<Address> addresses;


    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void AddressExt(Address address1 ){
        this.addresses.add(address1);
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
