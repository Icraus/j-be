package com.icraus.spring.dao;

import com.icraus.spring.entities.Customer;
import com.icraus.spring.entities.PhoneNumber;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DAOTest {
    private static String testSampleFilePath = "samples/countries.json";

    @BeforeAll
    public static void init(){
        PhoneNumber.init(testSampleFilePath);
    }
    @Autowired
    CustomerDAO dao;
    @Test
    public void testDAOCustomersByState(){
        int countStart = 0;
        assertNotNull(dao);
        List<Customer> customerList = dao.getCustomersByState(20, true);
        assertNotNull(customerList);
        assertEquals(20, customerList.size());
        for(var x : customerList){
            System.out.println(x.toJson());
        }
    }

    @Test
    public void testDAOCustomersByCountry(){
        int countStart = 0;
        assertNotNull(dao);
        List<Customer> customerList = dao.getCustomersByCountry(1, 20, "Morocco");
        assertNotNull(customerList);
        assertEquals(6, customerList.size());
        for(var x : customerList){
            System.out.println(x.toJson());
        }
    }
}
