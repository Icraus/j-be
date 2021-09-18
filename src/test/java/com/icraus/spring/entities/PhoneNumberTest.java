package com.icraus.spring.entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberTest {
    private static String testSampleFilePath = "samples/countries.json";
    @BeforeAll
    static void setUp() {
        PhoneNumber.init(testSampleFilePath);
    }

    @Test
    void testReadCountriesFile(){
        var result = PhoneNumber.getCountryByName("Cameroon");
        assertEquals("237", result.get("code"));
        assertEquals("\\(237\\)\\ ?[2368]\\d{7,8}$", result.get("regex"));
    }
    @Test
    void testReadPhoneNumberCategory(){
        String phoneNumber = "(258) 847651504";
//        "\\(258\\)\\ ?[28]\\d{7,8}$";
        String country = "Mozambique";
        String countryCode = "258";
        boolean isValid = true;
        PhoneNumber phoneNumberObject = new PhoneNumber(phoneNumber);
        assertEquals(country, phoneNumberObject.getCountry());
        assertEquals(countryCode, phoneNumberObject.getCountryCode());
        assertTrue(phoneNumberObject.isValid());
    }

    @Test
    void testReadPhoneNumberCategoryCameroon(){
        String phoneNumber = "(237) 697151594";
//        "\(237\)\ ?[2368]\d{7,8}$";
        String country = "Cameroon";
        String countryCode = "237";
        boolean isValid = true;
        PhoneNumber phoneNumberObject = new PhoneNumber(phoneNumber);
        assertEquals(country, phoneNumberObject.getCountry());
        assertEquals(countryCode, phoneNumberObject.getCountryCode());
        assertTrue(phoneNumberObject.isValid());
    }

}