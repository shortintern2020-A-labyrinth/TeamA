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


@Controller
@RequestMapping(path = "/")
public class HtmlController {

    @Autowired
    EmologService emologService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index() {
        return "friendlist";
    }

    @RequestMapping(path = "/emologlist", method = RequestMethod.GET)
    public String emologlist() {
        return "emologlist";
    }

//この処理いる？バッチ処理できたらいらなそうな気がする。
    @RequestMapping(path = "/enter", method = RequestMethod.GET)
    public String crawling(
            ModelMap modelMap
    ) throws Exception {

        emologService.createEmolog();

        return "friendlist";
    }
}
