package com.kurdi.springecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @GetMapping("/")
    public String guest()
    {
        return "products/index";
    }

    @GetMapping("/user")
    public String welcome(){return "welcome user";}

    @GetMapping("/admin")
    public String admin()
    {
        return "welcome admin";
    }


}
