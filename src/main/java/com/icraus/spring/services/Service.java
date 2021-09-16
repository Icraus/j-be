package com.icraus.spring.services;

import com.icraus.spring.dao.CustomerRepo;
import com.icraus.spring.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
class Service {

    @Autowired
    private CustomerRepo repo;

    @GetMapping("/customers")
    public String getAll(@RequestParam() Map<String,String> queryParams) {
        int start = 0;
        int end = 0;
        Boolean state = null;
        String country = "";
        String number = "";
        Long id;
        String name = "";
        if(queryParams.containsKey("start")){

        }
        return "";

    }
}
