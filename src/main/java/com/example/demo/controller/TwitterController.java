package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import twitter4j.*;

import java.io.IOException;

/**
 * @author Naoto Nishida
 */
@RestController
@RequestMapping(path = "/twitter")
public class TwitterController {

    /**
     * @author Naoto Nishida
     */
    @GetMapping("/search_keyword")
    public QueryResult search_keyowrd(@RequestParam(name = "searchword", defaultValue = "チンチン") String searchword) throws TwitterException {
        // 初期化
        Twitter twitter = new TwitterFactory().getInstance();
        Query query = new Query(searchword);

        QueryResult result = twitter.search(query);

        // for debug. show tweet results on CLI
        for (Status status : result.getTweets()) {
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
        }

        return result;
    }

    /**
     * @author Naoto Nishida
     */
    @RequestMapping(path = "/search_user", method = RequestMethod.GET)
    public QueryResult search_user(@RequestParam(name = "username", defaultValue = "CNN") String username,
                                    @RequestParam(name = "tweet_id", defaultValue = "1000000") long tweet_id
    ) throws TwitterException {
        // 初期化
        Twitter twitter = new TwitterFactory().getInstance();
        Query query = new Query();

        //ユーザーネームで指定.sinceId以降のtweetから15個取ってくる.最新のツイートから時系列順に取ってくるっぽい。
        query.setQuery("from:" + username);
        query.setSinceId(tweet_id);

        QueryResult result = twitter.search(query);

        //for debug on CLI.
        for (Status status : result.getTweets()) {
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
        }

        //TODO: ここで取ってきたLatestTweetIdを保存しておいて、次回からそのidのツイート以降のツイートを拾ってくればOK

        return result;
    }
}
