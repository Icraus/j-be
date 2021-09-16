package com.icraus.spring.services;

import com.icraus.spring.dao.CustomerDAO;
import com.icraus.spring.dao.CustomerRepo;
import com.icraus.spring.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/rest")
class CustomerService {
    @FunctionalInterface
    private interface Callable{
        List<Customer> call(int start, int count, String name);
    }
    private Integer getValue(String name, Map<String, String> queryMap ,Integer defaultValue){
        if(queryMap.containsKey(name)){
            return Integer.valueOf(queryMap.get(name));
        }
        return defaultValue;
    }

    private boolean getValue(String name, Map<String, String> queryMap ,boolean defaultValue){
        if(queryMap.containsKey(name)){
            return Boolean.valueOf(queryMap.get(name));
        }
        return defaultValue;
    }

    private Optional<String> getValueString(String name, Map<String, String> queryMap ,String defaultValue){
        if(queryMap.containsKey(name)){
            return Optional.of(queryMap.get(name));
        }
        return Optional.ofNullable(defaultValue);
    }

    private List<Customer> doOrGetAll(Map<String, String> queryParams, String nameValue, Callable obj){
        int start = getValue("start", queryParams, 0);
        int count = getValue("count", queryParams, 20);
        Optional<String> name = getValueString(nameValue, queryParams, null);
        Pageable page = PageRequest.of(start,count);
        if (name.isPresent())
            return obj.call(start, count, name.get());
        return repo.getRepo().findAll(page).getContent();
    }

    @Autowired
    private CustomerDAO repo;
    /** @Note:
     * I would have prefered using GraphQL.
     *
     */
    @GetMapping(value = "/customers", produces = "application/json")
    public List<Customer> doGetAllCustomer(@RequestParam(defaultValue = "start=0&count=20") Map<String,String> queryParams) {
        int start = getValue("start", queryParams, 0);
        int count = getValue("count", queryParams, 20);
        Pageable page = PageRequest.of(start,count);
        return repo.getRepo().findAll(page).getContent();

    }

    @GetMapping(value = "/customers/state", produces = "application/json")
    public List<Customer> doGetAllCustomerByState(@RequestParam(defaultValue = "start=0&count=20") Map<String,String> queryParams) {
        int start = getValue("start", queryParams, 0);
        int count = getValue("count", queryParams, 20);
        boolean state = getValue("state", queryParams, false);
        Pageable page = PageRequest.of(start,count);
        return repo.getCustomersByState(start, count, state);
    }

    @GetMapping(value = "/customers/country", produces = "application/json")
    public List<Customer> doGetAllCustomerByCountry(@RequestParam(defaultValue = "start=0&count=20") Map<String,String> queryParams) {
        return doOrGetAll(queryParams, "country",
                (start, count, c)-> {
                    return repo.getCustomersByCountry(start, count, c);
                });
    }

    @GetMapping(value = "/customers/countryCode", produces = "application/json")
    public List<Customer> doGetAllCustomerByCountryCode(@RequestParam(defaultValue = "start=0&count=20") Map<String,String> queryParams) {
        return doOrGetAll(queryParams, "countryCode",
                (start, count, c)-> {
                    return repo.getCustomersByCountryCode(start, count, c);
                });
    }

    @GetMapping(value = "/customers/phone", produces = "application/json")
    public List<Customer> doGetAllCustomerByPhone(@RequestParam(defaultValue = "start=0&count=20") Map<String,String> queryParams) {
        return doOrGetAll(queryParams, "phone",
                (start, count, c)-> {
                    Pageable page = PageRequest.of(start,count);
                    return repo.getRepo().getCustomersByPhone(c, page);
                });
    }

    @GetMapping(value = "/customers/name", produces = "application/json")
    public List<Customer> doGetAllCustomerByName(@RequestParam(defaultValue = "start=0&count=20") Map<String,String> queryParams) {
        return doOrGetAll(queryParams, "name",
                (start, count, c)-> {
                    Pageable page = PageRequest.of(start,count);
                    return repo.getRepo().getCustomersByName(c, page);
            });
    }

    @GetMapping(value = "/customers/{id}", produces = "application/json")
    public Customer doGetAllCustomerByName(@PathVariable() Long id) {
        return repo.getRepo().getCustomerById(id);
    }

}
