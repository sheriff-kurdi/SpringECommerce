package com.kurdi.springecommerce.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String guest()
    {
        return "welcome guest";
    }

    @GetMapping("/user")
    public String welcome(){return "welcome user";}

    @GetMapping("/admin")
    public String admin()
    {
        return "welcome admin";
    }


}
