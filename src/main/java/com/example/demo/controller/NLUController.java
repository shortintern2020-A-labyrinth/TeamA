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

@RestController
@RequestMapping(path = "/NLU")
public class NLUController {
    private static final Logger LOG = LoggerFactory.getLogger(ViewController.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public String index() {
        IamAuthenticator authenticator = new IamAuthenticator("fVfaYMA7tCh4zInWBhTBb5t69xQryK7ObKl42nampynG");
        NaturalLanguageUnderstanding naturalLanguageUnderstanding = new NaturalLanguageUnderstanding("2019-07-12", authenticator);
        naturalLanguageUnderstanding.setServiceUrl("https://api.jp-tok.natural-language-understanding.watson.cloud.ibm.com/instances/ac3df365-93f6-4255-beb8-620d66041251");
        String text = "ユーチューバー。2006年からYouTubeやってます。YouTube&SNSフォロワー計2000万人突破。再生回数計120億回突破。UUUM株式会社最高顧問&ファウンダー。インスタTikTokもフォロー是非 ！ DM読んでませんので、お仕事、その他コラボ依頼や動画SNS絡みのことは事務所まで";

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