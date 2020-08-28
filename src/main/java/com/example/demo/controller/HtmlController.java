package com.example.demo.controller;

import com.example.demo.common.EmologOutput;
import com.example.demo.service.EmologService;
import com.vdurmont.emoji.EmojiManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Naoto Nishida
 */
@Controller
@RequestMapping(path = "/")
public class HtmlController {

    @Autowired
    EmologService emologService;

    /**
     * @author Naoto Nishida
     */
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index() {
        return "friendlist";
    }

    /**
     * @author Naoto Nishida
     */
    @RequestMapping(path = "/emologlist", method = RequestMethod.GET)
    public String emologlist() {
        return "emologlist";
    }

    /**
     * @author Naoto Nishida
     */
//この処理いる？バッチ処理できたらいらなそうな気がする。
    @RequestMapping(path = "/enter", method = RequestMethod.GET)
    public String crawling(
            ModelMap modelMap
    ) throws Exception {

        emologService.createEmolog();

        //previous実装.バッチ処理したからいらなくなった。
        /**
         *     List<String> keyword_tweets = new ArrayList<String>();
         *         List<String> keyword_images = new ArrayList<String>();
         *
         *         keyword_tweets = ViewController.get_NLU_keywords("CNN", 100000);
         *         keyword_images = ViewController.get_image_keywords("CNN", 100000);
         *
         *         List<String> emojiList = new ArrayList<String>();
         *
         *         // TODO: ここでDBアクセスかなんかlook upする??。->つかささんのやつくっつける。
         *
         *         // 文字列整形の方(テキトーに::をつける方)
         *         for( String keyword : keyword_tweets){
         *             try{
         *                 String tmp = EmojiParser.parseToHtmlDecimal(":" + keyword + ":");
         *                 emojiList.add(tmp);
         *             }
         *             catch (NullPointerException ignored){}
         *         }
         *
         *         for( String keyword : keyword_images){
         *             try{
         *                 String tmp = EmojiParser.parseToHtmlDecimal(":" + keyword + ":");
         *                 emojiList.add(tmp);
         *             }
         *             catch (NullPointerException ignored){}
         *         }
         *
         *         // で、:hoge:みたいなんが得られたらEmologにパースして、それをaddAttributeする.->データがdbに入っていないとThymeleaf側で取り出せん模様。
         * //        modelMap.addAttribute("emologs", emojiList);
         *
         *
         *         // emojiList : ["&#128515", "&#128515", "&#128515"] みたいな感じ。
         *         // emojiListから一つの文字列に直す。
         *         String emolog = String.join("", emojiList);
         *
         *
         *         //TODO: ここで得られたemologをdbに登録する処理…
         *         // emolog(userid, friendid, create_at, contents)
         *         // userid = 1, friendid = 2, create_at = TIMESTAMP??, contents = emolog
         *
         *         return "friendlist";
         */

        return "friendlist";
    }
}
