package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.natural_language_understanding.v1.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public String read(@PathVariable("id") String id) {
        List<Map<String, Object>> list;
        list = jdbcTemplate.queryForList("select * from users where id = ?", id);
        return list.toString();
    }

    //TODO: Controllerのままか、返り値の型の調整どうするか考える(フロントとの兼ね合い。)
    @RequestMapping(path = "/twitter_to_NLU", method = RequestMethod.GET)
    public List<String> collect_NLU(@RequestParam(name = "username", defaultValue = "CNN") String username,
                                    @RequestParam(name = "tweet_id", defaultValue = "1000000") long tweet_id
    ) throws TwitterException {
        QueryResult result = search_user(username, tweet_id);
        List<String> NLU_results = null;

        for (Status status : result.getTweets()) {
            String tmp = NLU(status.getText());
            NLU_results.add(tmp);
        }

        return NLU_results;
    }

    // コントローラは関数として呼び出すのはキツイっぽいのでとりま関数として取り出してる。。
    //TODO: TwitterControllerやNLUControllerから共通部分を分離して別クラスとして保持。
    private QueryResult search_user(String username, long tweet_id) throws TwitterException {
        // 初期化
        Twitter twitter = new TwitterFactory().getInstance();
        Query query = new Query();

        //ユーザーネームで指定.sinceId以降のtweetから15個取ってくる.最新のツイートから時系列順に取ってくるっぽい。
        query.setQuery("from:" + username);
        query.setSinceId(tweet_id);

        QueryResult result = twitter.search(query);

        return result;
    }

    private String NLU(String text) {
        IamAuthenticator authenticator = new IamAuthenticator("fVfaYMA7tCh4zInWBhTBb5t69xQryK7ObKl42nampynG");
        NaturalLanguageUnderstanding naturalLanguageUnderstanding = new NaturalLanguageUnderstanding("2019-07-12", authenticator);
        naturalLanguageUnderstanding.setServiceUrl("https://api.jp-tok.natural-language-understanding.watson.cloud.ibm.com/instances/ac3df365-93f6-4255-beb8-620d66041251");

        CategoriesOptions categories= new CategoriesOptions.Builder()
                .limit(3)
                .build();

        Features features = new Features.Builder()
                .categories(categories)
                .build();

        AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                .text(text)
                .features(features)
                .build();

        AnalysisResults response = naturalLanguageUnderstanding
                .analyze(parameters)
                .execute()
                .getResult();
        List<String> resultCategories = new ArrayList<>();
        for (CategoriesResult r : response.getCategories()) {
            if (r.getScore() > 0.7) {
                String[] splitword = r.getLabel().split("/");
                resultCategories.add(splitword[splitword.length - 1]);
            }
        }


//        return resultCategories.toString();
        return response.getCategories().get(0).getLabel();
    }



}