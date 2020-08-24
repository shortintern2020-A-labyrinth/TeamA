package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.*;

@RestController
@RequestMapping(path = "/")
public class ViewController {
    private static final Logger LOG = LoggerFactory.getLogger(ViewController.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public String index() {
        List<Map<String, Object>> list;
        list = jdbcTemplate.queryForList("select * from users");
        return list.toString();
    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
    public String read(@PathVariable String id) {
        List<Map<String, Object>> list;
        list = jdbcTemplate.queryForList("select * from users where id = ?", id);
        return list.toString();
    }

    @RequestMapping(path = "/search_keyword", method = RequestMethod.GET)
    public String search_keyowrd() throws TwitterException {
        // 初期化
        Twitter twitter = new TwitterFactory().getInstance();
        Query query = new Query("チンチン");

        QueryResult result = twitter.search(query);
        for (Status status : result.getTweets()) {
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
        }

        return "a";
    }
}