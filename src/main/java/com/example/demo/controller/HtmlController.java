package com.example.demo.controller;

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
    public String crawling() throws Exception {

        List<String> keyword_tweets = new ArrayList<String>();
        List<String> keyword_images = new ArrayList<String>();

        keyword_tweets = ViewController.get_NLU_keywords("CNN", 100000);
        keyword_images = ViewController.get_image_keywords("CNN", 100000);

        // TODO: ここでDBアクセスかなんかlook upする??。

        // 文字列整形の方


        // で、:hoge:みたいなんが得られたらEmologにパースして、それをaddAttributeする.

        //TODO: ここで得られたemologをdbに登録する…

        return "friendlist";
    }
}
