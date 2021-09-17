package com.icraus.customerwebclient;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller

@CrossOrigin
public class Controller {
    @GetMapping("/")
    public String home(){
        return "html/index.html";
    }
}
