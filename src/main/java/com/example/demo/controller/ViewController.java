package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import twitter4j.TwitterException;

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
    public String read(@PathVariable("id") String id) {
        List<Map<String, Object>> list;
        list = jdbcTemplate.queryForList("select * from users where id = ?", id);
        return list.toString();
    }

    @RequestMapping(path = "/twitter_to_NLU", method = RequestMethod.GET)
    public List<String> collect_NLU(@RequestParam(name = "username", defaultValue = "CNN") String username,
                                    @RequestParam(name = "tweet_id", defaultValue = "1000000") long tweet_id
    ) throws TwitterException {



        return ;
    }
}