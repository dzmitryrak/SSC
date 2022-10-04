package com.itechart.models;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
public class Account {
    @SerializedName("Name")
    final String accountName;
    @SerializedName("Type")
    final String type;
    @SerializedName("Website")
    final String website;
    @SerializedName("Industry")
    final String industry;
    @SerializedName("Phone")
    final String phone;
    @SerializedName("Description")
    final String description;
    @SerializedName("NumberOfEmployees")
    final String numberOfEmployees;
    final String billingStreet;
    final String billingCity;
    final String billingPostalCode;
    final String billingState;
    final String billingCountry;
    final String shippingStreet;
    final String shippingCity;
    final String shippingState;
    final String shippingPostalCode;
    final String shippingCountry;
    BillingAddress billingAddress;
    ShippingAddress shippingAddress;
    @SerializedName("OwnerId")
    final String accountOwner;

    public String getName() {
        return accountName;
    }

    @Data
    @AllArgsConstructor
    public static class BillingAddress {
        String city;
        String country;
        String geocodeAccuracy;
        String latitude;
        String longitude;
        String postalCode;
        String state;
        String street;
    }

    @Data
    @AllArgsConstructor
    public static class ShippingAddress {
        String city;
        String country;
        String geocodeAccuracy;
        String latitude;
        String longitude;
        String postalCode;
        String state;
        String street;
    }
}
