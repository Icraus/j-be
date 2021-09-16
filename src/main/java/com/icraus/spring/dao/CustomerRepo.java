package com.icraus.spring.dao;

import com.icraus.spring.entities.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CustomerRepo extends PagingAndSortingRepository<Customer, Long> {
    List<Customer> findAll();
    List<Customer> getCustomersByPhone(String s, Pageable page);
    List<Customer> getCustomersByName(String s, Pageable page);
    Customer getCustomerById(Long id);
}

