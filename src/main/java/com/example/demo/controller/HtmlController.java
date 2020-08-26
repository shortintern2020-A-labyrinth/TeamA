package com.example.demo.controller;

import com.vdurmont.emoji.EmojiManager;
import org.springframework.ui.ModelMap;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(path = "/")
public class HtmlController {
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(path = "/enter", method = RequestMethod.GET)
    public String crawling(
            ModelMap modelMap
    ) throws Exception {

        List<String> keyword_tweets = new ArrayList<String>();
        List<String> keyword_images = new ArrayList<String>();

        keyword_tweets = ViewController.get_NLU_keywords("CNN", 100000);
        keyword_images = ViewController.get_image_keywords("CNN", 100000);

        List<String> emojiList = new ArrayList<String>();

        // TODO: ここでDBアクセスかなんかlook upする??。->つかささんのやつくっつける。

        // 文字列整形の方(テキトーに::をつける方)
        for( String keyword : keyword_tweets){
            try{
                String tmp = EmojiManager.getForAlias(":" + keyword + ":").getUnicode();
                emojiList.add(tmp);
            }
            catch (NullPointerException ignored){}
        }

        for( String keyword : keyword_images){
            try{
                String tmp = EmojiManager.getForAlias(":" + keyword + ":").getUnicode();
                emojiList.add(tmp);
            }
            catch (NullPointerException ignored){}
        }

        // で、:hoge:みたいなんが得られたらEmologにパースして、それをaddAttributeする.->取り出せん。
//        modelMap.addAttribute("emologs", emojiList);

        //TODO: ここで得られたemologをdbに登録する…


        return "friendlist";
    }
}
