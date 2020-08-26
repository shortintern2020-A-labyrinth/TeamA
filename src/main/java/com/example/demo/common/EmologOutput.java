package com.example.demo.common;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.natural_language_understanding.v1.model.*;
import com.ibm.watson.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.visual_recognition.v3.model.*;
import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import org.apache.commons.lang3.StringUtils;
import org.seasar.doma.internal.util.StringUtil;
import twitter4j.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class EmologOutput {


    /** ユーザ名とidによるツイートテキストからキーワードのリストを抽出
     //input:  String username
     //input:  long tweet_id
     //output: Stringlist keywords
     */
    public List<String> collect_NLU_keywords_from_tweets(String username, long tweet_id
    ) throws TwitterException {
        QueryResult result = search_user(username, tweet_id);
        ArrayList<String> NLU_results = new ArrayList<>();

        for (Status status : result.getTweets()) {
            String tmp = NLU(status.getText());
            if(!StringUtils.isBlank(tmp)) {
//                System.out.println(tmp);
                NLU_results.add(tmp);
            }
        }
        return NLU_results;
    }


    /** ユーザ名とidによるツイート画像からキーワードのリストを抽出
     //input:  String username
     //input:  long tweet_id
     //output: StringList keywords
     */
    public List<String> collect_image_keywords_from_tweets(String username, long tweet_id
    ) throws Exception {
        QueryResult result = search_user(username, tweet_id);
        ArrayList<String> imageprocessing_results = new ArrayList<>();

        for (Status status : result.getTweets()) {
            if(status.getMediaEntities().length > 0) {
//                System.out.println(status.getMediaEntities()[0].getMediaURL());

                String tmp = imageProcessing(status.getMediaEntities()[0].getMediaURL());
                if(!StringUtils.isBlank(tmp)) {
//                    System.out.println(tmp);
                    imageprocessing_results.add(tmp);
                }
            }
        }

        return imageprocessing_results;
//                ["call center", "people", "newsreader"]みたいなんがくる。

    }


    /** ユーザ名とidからツイート検索
     //input:  String username
     //input:  long tweet_id
     //output: QueryResult
     */
    //TODO: TwitterControllerやNLUControllerから共通部分を分離して別クラスとして保持。
    private QueryResult search_user(String username, long tweet_id) throws TwitterException {
        // 初期化
        Twitter twitter = new TwitterFactory().getInstance();
        Query query = new Query();

        //ユーザーネームで指定.sinceId以降のtweetから15個取ってくる.最新のツイートから時系列順に取ってくるっぽい。
        query.setQuery("from:" + username);
        query.setSinceId(tweet_id);

        return twitter.search(query);
    }


    /** 文字列からキーワードを抽出する
     //input:  String text
     //output: String keyword
     */
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

//
    /** 画像urlからキーワードを抽出する
     //input:  String imageUrl
     //output: String keyword
     */
    private String imageProcessing(String url) throws FileNotFoundException {
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


    /** emoji-javaを使ってキーワードから絵文字に変換
     //input:  String keyword
     //output: emoji (Unicode)
     */
    public String convert(String keyword) {
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

     /** text to keyword
     //input:  (username, tweet_id)
     //output: ArrayList keywords
     */
    public List<String> get_NLU_keywords(String username, long tweet_id) throws TwitterException {
        QueryResult result = search_user(username, tweet_id);
        ArrayList<String> keywords = new ArrayList<>();

        for (Status status : result.getTweets()) {
            String tmp = NLU(status.getText());
            if(tmp != null) {
//                System.out.println(tmp);
                keywords.add(tmp);
            }
        }
        return keywords;
        //TODO: 整形する必要あるかも？
    }

    /** image to keyword
    //input:  (username, tweet_id)
    //output: ArrayList keywords
     */
    public List<String> get_image_keywords(String username, long tweet_id) throws Exception {
        QueryResult result = search_user(username, tweet_id);
        ArrayList<String> keywords = new ArrayList<>();

        for (Status status : result.getTweets()) {
            if(status.getMediaEntities().length > 0) {
//                System.out.println(status.getMediaEntities()[0].getMediaURL());

                String tmp = imageProcessing(status.getMediaEntities()[0].getMediaURL());
                if (tmp != null) {
//                    System.out.println(tmp);
                    keywords.add(tmp);
                }
            }
        }

        return keywords;
//                ["call center", "people", "newsreader"]みたいなんがくる。

    }




}
