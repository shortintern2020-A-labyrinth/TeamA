package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.visual_recognition.v3.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping(path = "/Image")
public class ImageController {
    private static final Logger LOG = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public String test() {
        IamAuthenticator authenticator = new IamAuthenticator("8nwN0eKp7eZ73So4DLdPndp_yv-vtlI27pN1wK2TjVg1");
        VisualRecognition visualRecognition = new VisualRecognition("2019-07-12", authenticator);
//        visualRecognition.setServiceUrl("https://zukan.pokemon.co.jp/zukan-api/up/images/index/");

        System.out.println("Classify an image");
        ClassifyOptions options = new ClassifyOptions.Builder()
                .url("https://zukan.pokemon.co.jp/zukan-api/up/images/index/94b9fb82b38847b83a8041e9a78989ce.png")
                .build();
        ClassifiedImages result = visualRecognition.classify(options).execute().getResult();
        System.out.println(result);
        return "asdfg";
    }

}