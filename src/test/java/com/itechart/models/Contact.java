package com.itechart.models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
public class Contact {
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
    @SerializedName("Department")
    final String department;
    @SerializedName("Fax")
    final String fax;
    @SerializedName("Phone")
    final String phone;
    @SerializedName("MobilePhone")
    final String mobilePhone;
    @SerializedName("Name")
    String name;

    public String getName() {
        name = getSalutation() + " " + getFirstName() + " " + getLastName();
        log.warn("Compiling Contact Name: {}", name);
        return name;
    }
}
