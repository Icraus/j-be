package com.icraus.spring;

import com.icraus.spring.entities.PhoneNumber;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {
    private static String testSampleFilePath = "samples/countries.json";

    @BeforeAll
    static void initTest(){
        PhoneNumber.init(testSampleFilePath);
    }
    @Test
    void contextLoads() {
    }

}
