package com.example.demo.controller;


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
@RequestMapping(path = "/")
public class ChatController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(path = "/chat", method = RequestMethod.GET)
    public String chat(Model model){
        //日付
        //Date date = new Date();

        String date = "8月18日";
        model.addAttribute("date", date);

        //絵文字
        //データを受け取る
        int u_id = 1;
        int f_id = 2;
        //SELECT contents FROM emolog WHERE userid== AND friendid AND create_at==date;
        /*List<Map<String, Object>> emojilist;
        emojilist = jdbcTemplate.queryForList("SELECT * FROM emolog");
        String MyEmolog ="";
        String FriendEmolog ="";
        for(Map<String, Object> map : emojilist) {
            if(u_id == (Integer)map.get("userid") && f_id == (Integer)map.get("friendid") ){ 
                //本当は一日分なので日付も欲しいですが
                MyEmolog += map.get("contents");
            }else if(f_id == (Integer)map.get("userid") && u_id == (Integer)map.get("friendid")){
                FriendEmolog +=map.get("contents");
             }
        }*/

        //絵文字の表示部分
        //相手側
        //String text = "An 😀awesome 😃string with a few 😉emojis!";
        String text = ":grinning: :smiley: :wink: :radio: :credit_card: ";
        //String text =FriendEmolog;
        text = EmojiParser.parseToUnicode(text);
        model.addAttribute("FriendEmolog", text);
        //自分側
        //text=MyEmolog;
        text = EmojiParser.parseToUnicode(text);
        model.addAttribute("MyEmolog", text);


         //チャット部分の表示
         /*List<Map<String, Object>> list;
         //list = jdbcTemplate.queryForList("select * from users");
         list = jdbcTemplate.queryForList("SELECT * FROM talk");
         
         String MyMessage ="";
         String FriendMessage ="";
         for(Map<String, Object> map : list) {
             if(u_id == (Integer)map.get("userid") && f_id == (Integer)map.get("friendid") ){ 
                 //本当は一日分なので日付も欲しいですが
                MyMessage += map.get("contents");
             }else if(f_id == (Integer)map.get("userid") && u_id == (Integer)map.get("friendid")){
                FriendMessage +=map.get("contents");
             }
         }
         model.addAttribute("MyChatMessage", MyMessage);
         model.addAttribute("FriendChatMessage", FriendMessage);*/
         
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