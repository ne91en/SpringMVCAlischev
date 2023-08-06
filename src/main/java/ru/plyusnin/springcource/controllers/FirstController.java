package ru.plyusnin.springcource.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/first")
@Controller
public class FirstController {
    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String surname,
                            Model model) {

//        System.out.println("hello " + name + " " + surname);
        model.addAttribute("message", "hello " + name + " " + surname);
        return "first/hello";
    }
    @GetMapping("/goodbye")
    public String goodByePage(){
        return "first/goodbye";
    }
    @GetMapping("/calculator")
    public String calculator(@RequestParam("a") int a,
                             @RequestParam("b") int b,
                             @RequestParam("action") String action,
                             Model model){
        int sum = 0;
        switch (action){
            case "additional": sum=a+b;
            break;
            case "subtraction": sum=a-b;
            break;
            case "multiplication" : sum=a*b;
            break;
            case "division" : sum=a/b;
            break;
        }
        model.addAttribute("sum", sum);
        return "first/calculator";
    }
}
