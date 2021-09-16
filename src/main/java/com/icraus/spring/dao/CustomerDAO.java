package com.icraus.spring.dao;

import com.icraus.spring.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

public class CustomerDAO {
    @Autowired
    CustomerRepo repo;

    public Iterable<Customer> getCustomersByCountry(int start, int count, String countryName){
       return repo.findAll().stream().filter(e -> e.getCountry().equals(countryName)).skip(start).limit(count).collect(Collectors.toList());
    }

    public Iterable<Customer> getCustomersByCountryCode(int start, int count, String countryCode){
        return repo.findAll().stream().filter(e -> e.getCountryCode().equals(countryCode)).skip(start).limit(count).collect(Collectors.toList());
    }

    public Iterable<Customer> getCustomersByState(int start, int count, boolean state){
        return repo.findAll().stream().filter(e -> e.isValid() == state).skip(start).limit(count).collect(Collectors.toList());
    }

    public CustomerRepo getRepo(){
        return repo;
    }


}
