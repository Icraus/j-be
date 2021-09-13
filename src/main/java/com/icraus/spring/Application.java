package com.icraus.spring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
@RestController
class Service{
    @Autowired
    CustomerRepo repo;
    @GetMapping("/message")
    public String getMessage(@RequestParam() String name){
        Customer u = new Customer();
        u.setName(name);
        repo.save(u);
        return repo.findAllByName(name).stream().map(Customer::getName).reduce("", (e, b) -> e + b);
    }
}
