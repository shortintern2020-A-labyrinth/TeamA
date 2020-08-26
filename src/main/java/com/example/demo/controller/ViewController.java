package com.example.demo.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.NullPointerException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.example.demo.entity.Emolog;
import com.example.demo.service.EmologService;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.natural_language_understanding.v1.model.*;
import com.ibm.watson.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.visual_recognition.v3.model.*;
import com.vdurmont.emoji.*;
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

    @Autowired
    EmologService emologService;

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
    @RequestMapping(path = "/twitter_NLU_keyword", method = RequestMethod.GET)
    public List<String> collect_NLU_keywords_from_tweets(
                                    @RequestParam(name = "username", defaultValue = "CNN") String username,
                                    @RequestParam(name = "tweet_id", defaultValue = "1000000") long tweet_id
    ) throws TwitterException {
        QueryResult result = search_user(username, tweet_id);
        ArrayList<String> NLU_results = new ArrayList<>();

        for (Status status : result.getTweets()) {
            String tmp = NLU(status.getText());
            if(tmp != null) {
//                System.out.println(tmp);
                NLU_results.add(tmp);
            }
        }

        return NLU_results;
        //TODO: 整形する必要あるかも？
    }


    @RequestMapping(path = "/twitter_image_keyword", method = RequestMethod.GET)
    public List<String> collect_image_keywords_from_tweets(
            @RequestParam(name = "username", defaultValue = "CNN") String username,
            @RequestParam(name = "tweet_id", defaultValue = "1000000") long tweet_id
    ) throws Exception {
        QueryResult result = search_user(username, tweet_id);
        ArrayList<String> imageprocessing_results = new ArrayList<>();

        for (Status status : result.getTweets()) {
            if(status.getMediaEntities().length > 0) {
//                System.out.println(status.getMediaEntities()[0].getMediaURL());

                String tmp = ImageProcessing(status.getMediaEntities()[0].getMediaURL());
                if (tmp != null) {
//                    System.out.println(tmp);
                    imageprocessing_results.add(tmp);
                }
            }
        }

        return imageprocessing_results;
//                ["call center", "people", "newsreader"]みたいなんがくる。

    }




    // コントローラは関数として呼び出すのはキツイっぽいのでとりま関数として取り出してる。。
    //TODO: TwitterControllerやNLUControllerから共通部分を分離して別クラスとして保持。
    private static QueryResult search_user(String username, long tweet_id) throws TwitterException {
        // 初期化
        Twitter twitter = new TwitterFactory().getInstance();
        Query query = new Query();

        //ユーザーネームで指定.sinceId以降のtweetから15個取ってくる.最新のツイートから時系列順に取ってくるっぽい。
        query.setQuery("from:" + username);
        query.setSinceId(tweet_id);

        return twitter.search(query);
    }

    private static String NLU(String text) {
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


    private static String ImageProcessing(String url) throws FileNotFoundException {

        IamAuthenticator authenticator = new IamAuthenticator("8nwN0eKp7eZ73So4DLdPndp_yv-vtlI27pN1wK2TjVg1");
        VisualRecognition visualRecognition = new VisualRecognition("2019-07-12", authenticator);

        ClassifyOptions options = new ClassifyOptions.Builder()
                .url(url)
                .build();
        ClassifiedImages result = visualRecognition.classify(options).execute().getResult();
        System.out.println(result);

        List<ClassifiedImage> images = result.getImages();

        ClassifiedImage image = images.get(0);
        ClassifierResult r = image.getClassifiers().get(0);
        ClassResult c = r.getClasses().get(0);

        return c.getXClass();

    }

    //emoji-javaを使ってキーワードから絵文字に変換
    @RequestMapping(path = "/convert/{keyword}", method = RequestMethod.GET)
    public String convert(
            @PathVariable("keyword") String keyword
    ) {
        keyword = keyword.replace(" ", "_"); //空白を_に置換
        keyword = ":" + keyword + ":";
        Emoji emoji;
        try {
            emoji = EmojiManager.getForAlias(keyword);
            System.out.println(emoji);
            return emoji.getUnicode();
        } catch(java.lang.NullPointerException n) {
            //キーワードがエイリアスとして存在していないときは？を返す
            System.out.println(n);
            emoji = EmojiManager.getForAlias("grey_question");
            return emoji.getUnicode();
        }
    }

    @RequestMapping(value="/emolog",method=RequestMethod.GET)
    public List<Emolog> insertEmolog(
            Integer user,
            Integer friend,
            String emoji
    ) throws ParseException {
//        return emologService.insert(user, friend, emoji);
        return emologService.insert(123, 456, "U+1F600");
    }
    //関数もおいちゃえ
    public static List<String> get_NLU_keywords(String username, long tweet_id) throws TwitterException {
        QueryResult result = search_user(username, tweet_id);
        ArrayList<String> NLU_results = new ArrayList<>();

        for (Status status : result.getTweets()) {
            String tmp = NLU(status.getText());
            if(tmp != null) {
//                System.out.println(tmp);
                NLU_results.add(tmp);
            }
        }

        return NLU_results;
        //TODO: 整形する必要あるかも？
    }


    public static List<String> get_image_keywords(String username, long tweet_id) throws Exception {
        QueryResult result = search_user(username, tweet_id);
        ArrayList<String> imageprocessing_results = new ArrayList<>();

        for (Status status : result.getTweets()) {
            if(status.getMediaEntities().length > 0) {
//                System.out.println(status.getMediaEntities()[0].getMediaURL());

                String tmp = ImageProcessing(status.getMediaEntities()[0].getMediaURL());
                if (tmp != null) {
//                    System.out.println(tmp);
                    imageprocessing_results.add(tmp);
                }
            }
        }

        return imageprocessing_results;
//                ["call center", "people", "newsreader"]みたいなんがくる。

    }

}