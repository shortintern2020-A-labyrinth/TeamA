package com.example.demo.controller;

import com.example.demo.entity.Friend;
import com.example.demo.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Controller
@RequestMapping(path = "/")
public class FriendController {

    /*@Autowired
    FriendService service;

    @RequestMapping(value="/{userid}",method=RequestMethod.GET)
    public String selectAll(@PathVariable("userid") String userid, Model model) {
        int userId = Integer.parseInt(userid);
        List<Friend> friends = service.selectAll(userId);
        model.addAttribute("friends", friends);
        return "friendlist.html";
    }*/

    @RequestMapping(path = "/friendlist")
    public String chat(Model model) {
        return "friendlist";
    }
}