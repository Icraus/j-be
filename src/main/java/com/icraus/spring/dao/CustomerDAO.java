package com.icraus.spring.dao;

import com.icraus.spring.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerDAO {

    @Autowired
    CustomerRepo repo;
    /** @Notes:
        FIXME: In production i would usually use SQL queries to avoid Memory/performance issues
         we can actually do the regex search in the DB itself and SQLLite3 can
         Do that but this is just for simplicity
    */
    public List<Customer> getCustomersByCountry(int start, int count, String countryName){
       return repo.findAll().stream().filter(e -> e.getCountry().equals(countryName)).skip(start).limit(count).collect(Collectors.toList());
    }
    public Long getCustomersCountByCountry(String c){
        return repo.findAll().stream().filter(e -> e.getCountry().equals(c)).count();
    }

    public List<Customer> getCustomersByCountry(int count, String countryName){
        return getCustomersByCountry(0, count, countryName);
    }

    public List<Customer> getCustomersByCountryCode(int start, int count, String countryCode){
        return repo.findAll().stream().filter(e -> e.getCountryCode().equals(countryCode)).skip(start).limit(count).collect(Collectors.toList());
    }

    public List<Customer> getCustomersByCountryCode(int count, String countryCode){
        return getCustomersByCountryCode(0, count, countryCode);
    }

    public Long getCustomersCountByCountryCode(String c){
        return repo.findAll().stream().filter(e -> e.getCountryCode().equals(c)).count();
    }

    public List<Customer> getCustomersByState(int start, int count, boolean state){
        return repo.findAll().stream().filter(e -> e.isValid() == state).skip(start).limit(count).collect(Collectors.toList());
    }

    public List<Customer> getCustomersByState(int count, boolean state){
        return getCustomersByState(0, count, state);
    }
    public Long getCustomersCountByCountryState(boolean state){
        return repo.findAll().stream().filter(e -> e.isValid() == state).count();
    }


    public CustomerRepo getRepo(){
        return repo;
    }


}
