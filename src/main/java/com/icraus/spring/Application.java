package com.icraus.spring;
import com.icraus.spring.dao.CustomerRepo;
import com.icraus.spring.entities.Customer;
import com.icraus.spring.entities.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        PhoneNumber.init("samples/countries.json");
        SpringApplication.run(Application.class, args);
    }

}
