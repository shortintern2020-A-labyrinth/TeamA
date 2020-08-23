package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String View(){
        return "view";
    }
    /*
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String SayHello(Model model) {
        model.addAttribute("msg", "Hello World!!!");
        return "view";
    }*/
}