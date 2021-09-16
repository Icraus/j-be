package com.icraus.spring.entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
}