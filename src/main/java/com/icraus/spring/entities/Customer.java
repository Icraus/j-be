package com.icraus.spring.entities;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icraus.spring.utils.JSONify;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "customer")
public class Customer implements JSONify {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, name = "name")
    private String name = "";

    private String phone = "";

    @Transient
    private PhoneNumber mPhoneNumber;


    public String getPhone() {
        if(mPhoneNumber == null){
            setPhone(phone);
        }
        return phone;
    }

    @Column(nullable = false, name = "phone")
    @Access(AccessType.PROPERTY)
    public void setPhone(String phone) {
        this.phone = phone;
        this.mPhoneNumber = null;
        this.mPhoneNumber = new PhoneNumber(phone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Customer customer = (Customer) o;
        return getName().equals(customer.getName()) && getPhone().equals(customer.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPhone());
    }



    public Customer(){
    }

    public Customer(long id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.setPhone(phone);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry(){
        if(mPhoneNumber == null){
            setPhone(phone);
        }
        return mPhoneNumber.getCountry();
    }
    public String getCountryCode(){
        if(mPhoneNumber == null){
            setPhone(phone);
        }
        return mPhoneNumber.getCountryCode();
    }
    public boolean isValid(){
        if(mPhoneNumber == null){
            setPhone(phone);
        }
        return mPhoneNumber.isValid();
    }

    public static List<Customer> fromJson(String jsonString){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return Arrays.stream(mapper.readValue(jsonString, Customer[].class)).collect(Collectors.toList());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}

