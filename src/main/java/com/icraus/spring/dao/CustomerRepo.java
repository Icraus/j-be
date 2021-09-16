package com.icraus.spring.dao;

import com.icraus.spring.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CustomerRepo extends PagingAndSortingRepository<Customer, Long> {
    List<Customer> findAll();
}

