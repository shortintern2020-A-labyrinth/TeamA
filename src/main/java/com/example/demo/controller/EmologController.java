package com.example.demo.controller;

import com.example.demo.entity.Emolog;
import com.example.demo.entity.Friend;
import com.example.demo.entity.Sample;
import com.example.demo.service.EmologService;
import com.example.demo.service.FriendService;
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

/**
 * @author Yuta Takayama
 */
@Controller
@RequestMapping(path = "/emologlist")
public class EmologController {

    @Autowired
    EmologService service;

    @Autowired
    FriendService friendService;

    @RequestMapping(value="/{userid}/{friendid}",method=RequestMethod.GET)
    public String selectAll(@PathVariable("userid") String userid, @PathVariable("friendid") String friendid, Model model) {
        int userId = Integer.parseInt(userid);
        int friendId = Integer.parseInt(friendid);
        List<Emolog> emologs = service.selectAll(userId, friendId);
        Friend friend = friendService.select(userId, friendId);
        model.addAttribute("emologs", emologs);
        model.addAttribute("friend", friend);
        return "emologlist";
    }

    @RequestMapping(path = "/emologlist")
    public String chat(Model model){
        return "emologlist";
    }
}