package com.example.demo.controller;

import com.example.demo.entity.Emolog;
import com.example.demo.entity.Sample;
import com.example.demo.service.EmologService;
import com.example.demo.service.SampleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(path = "/emologlist")
public class EmologController {

    @Autowired
    EmologService service;

    @RequestMapping(value="/{userid}/{friendid}",method=RequestMethod.GET)
    public String selectAll(@PathVariable("userid") String userid, @PathVariable("friendid") String friendid, Model model) {
        int userId = Integer.parseInt(userid);
        int friendId = Integer.parseInt(friendid);
        List<Emolog> emologs = service.selectAll(userId, friendId);
        model.addAttribute("emologs", emologs);
        return "emologlist";
    }

}