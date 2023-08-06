package ru.plyusnin.springcource.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/first")
@Controller
public class FirstController {
    @GetMapping("/hello")
    public String helloPage(@RequestParam("name") String name) {
        System.out.println(name);
        return "first/hello";
    }
    @GetMapping("/goodbye")
    public String goodByePage(){
        return "first/goodbye";
    }
}
