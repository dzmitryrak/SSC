package com.itechart.models;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
public class Lead {
    @SerializedName("Status")
    final String status;
    @SerializedName("Salutation")
    final String salutation;
    @SerializedName("FirstName")
    final String firstName;
    @SerializedName("LastName")
    final String lastName;
    @SerializedName("Title")
    final String title;
    @SerializedName("Email")
    final String email;
    @SerializedName("Phone")
    final String phone;
    @SerializedName("MobilePhone")
    final String mobilePhone;
    @SerializedName("Rating")
    final String rating;
    @SerializedName("Website")
    final String website;
    @SerializedName("Company")
    final String company;
    @SerializedName("Industry")
    final String industry;
    @SerializedName("NumberOfEmployees")
    final String numberOfEmployees;
    @SerializedName("LeadSource")
    final String leadSource;
    @SerializedName("street")
    final String street;
    @SerializedName("city")
    final String city;
    @SerializedName("country")
    final String country;
    @SerializedName("Name")
    String name;
    @SerializedName("Address")
    Address address;

    public String getName() {
        name = getSalutation() + " " + getFirstName() + " " + getLastName();
        log.warn("Compiling Lead Name: {}", name);
        return name;
    }

    @Data
    @AllArgsConstructor
    public static class Address {
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
