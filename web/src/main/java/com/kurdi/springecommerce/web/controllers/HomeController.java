package com.kurdi.springecommerce.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String guest()
    {
        return "shared/index";
    }

    @GetMapping("/user")
    public String welcome(){return "welcome user";}

    @GetMapping("/admin")
    public String admin()
    {
        return "welcome admin";
    }


}
