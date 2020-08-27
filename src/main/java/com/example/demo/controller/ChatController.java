package com.example.demo.controller;


import com.example.demo.entity.Emolog;
import com.example.demo.service.EmologService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

//import java.util.Date;
import com.vdurmont.emoji.EmojiParser;

import org.springframework.stereotype.Controller;

//chatページの表示
@Controller
@RequestMapping(path = "/chat")
public class ChatController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    EmologService service;

    @RequestMapping(path = "/{emologid}", method = RequestMethod.GET)
    public String chat(@PathVariable("emologid") String emologid, Model model){
        int friendemologId = Integer.parseInt(emologid);
        //日付
        //Date date = new Date();

        String date = "8月18日";
        model.addAttribute("date", date);


        int u_id = 1;
        int f_id = 2;

        Emolog friendEmolog = service.selectById(friendemologId);
        Emolog myEmolog = service.selectByDay(friendEmolog);

        //絵文字の表示部分
        //相手側
        model.addAttribute("FriendEmolog", friendEmolog);
        //自分側
        //text=MyEmolog;
        model.addAttribute("MyEmolog", myEmolog);


        return "chat";
    }

    /*自分用
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String twitter_connect(Model model){
        model.addAttribute("MyEmolog", "Hello Springboot");
        return "chat";
    }*/


    //テキストボックス入力
    /*@RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String TextBox(Model model){
        // 空のフォームオブジェクトをModelに設定
		model.addAttribute("MessageBox", new Message());
        // 遷移先を返す(この場合はinput.htmlが遷移先となる)
        return "chat";
    }*/

    /*@RequestMapping(value = "/chat", method = RequestMethod.POST)
	public String confirm(@ModelAttribute("MessageBox") Message form,Model model) {
        // 空のフォームオブジェクトをModelに設定
        model.addAttribute("MessageBox", new Message());
        
        // 遷移先を返す(この場合はconfirm.htmlが遷移先となる)
		return "chat";
	}*/


    //private static final Logger LOG = LoggerFactory.getLogger(ViewController.class);
/*
    @Autowired
    JdbcTemplate jdbcTemplate;


    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public String index() {
        List<Map<String, Object>> list;
        list = jdbcTemplate.queryForList("select * from users");
        return list.toString();
    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
    public String read(@PathVariable("id") String id) {
        List<Map<String, Object>> list;
        list = jdbcTemplate.queryForList("select * from users where id = ?", id);
        return list.toString();
    }


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String twitter_connect() throws Exception{
        return "";
    }*/

}