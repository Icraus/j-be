package com.icraus.spring.entities;

import org.assertj.core.api.Fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    private static String testSampleFilePath = "samples/countries.json";

    @BeforeAll
    static void setUp() {
        PhoneNumber.init(testSampleFilePath);
    }

    @Test
    void testCreateCustomer(){
        Customer c = new Customer(1, "Ahmed", "(258) 847651504");
        String country = "Mozambique";
        String countryCode = "258";
        boolean isValid = true;
        assertEquals(country, c.getCountry());
        assertEquals(countryCode, c.getCountryCode());
        assertEquals(isValid, c.isValid());
        Customer invalid = new Customer(1, "Ahmed", "(258) 8504");
        assertEquals(country, invalid.getCountry());
        assertEquals(countryCode, invalid.getCountryCode());
        assertFalse(invalid.isValid());
    }
    @Test
    void testSerializeCustomerToJSON(){
        Customer c = new Customer(1, "Ahmed", "(258) 847651504");
        String country = "Mozambique";
        String countryCode = "258";
        boolean isValid = true;
        assertEquals(country, c.getCountry());
        assertEquals(countryCode, c.getCountryCode());
        assertEquals(isValid, c.isValid());
        Customer invalid = new Customer(1, "Ahmed", "(258) 8504");
        assertEquals(country, invalid.getCountry());
        assertEquals(countryCode, invalid.getCountryCode());
        assertFalse(invalid.isValid());
    }
    @Test
    void testCreateCustomerFromJSON(){
        Customer c = new Customer(1, "Ahmed", "(258) 847651504");
        String jsonString = null;
        try{
            jsonString =new BufferedReader(new InputStreamReader(new FileInputStream("samples/customer.json"))).lines().parallel().collect(Collectors.joining("\n"));
        }catch (Exception ex){
            ex.printStackTrace();
            fail(ex.toString());
        }
        var customerList = Customer.fromJson(jsonString);
        var actualCustomer = customerList.get(0);
        System.out.println(c.toJson());
        System.out.println(actualCustomer.toJson());
        assertEquals(c, actualCustomer);
        assertEquals(c.getCountry(), actualCustomer.getCountry());
        assertEquals(c.isValid(), actualCustomer.isValid());
        Customer invalid = new Customer(50, "Ahmed", "(258) 8504");
    }

    @Test
    void testCreateCustomerFromJSONInvalid() {
        String jsonString = null;
        try{
            jsonString =new BufferedReader(new InputStreamReader(new FileInputStream("samples/customer.json"))).lines().parallel().collect(Collectors.joining("\n"));
        }catch (Exception ex){
            ex.printStackTrace();
            fail(ex.toString());
        }
        var customerList = Customer.fromJson(jsonString);
        var actualCustomerInvalid = customerList.get(1);
        Customer invalid = new Customer(50, "Ahmed", "(258) 8504");
        assertEquals(invalid, actualCustomerInvalid);
        assertFalse(actualCustomerInvalid.isValid());

    }
    @Test
    void testCreateCustomerFromJSONNoPhone() {
        String jsonString = null;
        try{
            jsonString =new BufferedReader(new InputStreamReader(new FileInputStream("samples/customer.json"))).lines().parallel().collect(Collectors.joining("\n"));
        }catch (Exception ex){
            ex.printStackTrace();
            fail(ex.toString());
        }
        var customerList = Customer.fromJson(jsonString);
        var actualCustomerNoPhone = customerList.get(2);
        System.out.println(actualCustomerNoPhone.toJson());
        Customer invalid = new Customer(50, "Ahmed", "(258) 8504");
        assertEquals("", actualCustomerNoPhone.getPhone());
        assertEquals("", actualCustomerNoPhone.getCountryCode());
        assertEquals("", actualCustomerNoPhone.getCountry());

    }

}