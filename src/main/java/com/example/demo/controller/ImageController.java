package com.example.demo.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.natural_language_understanding.v1.model.CategoriesResult;
import com.ibm.watson.natural_language_understanding.v1.model.Features;
import com.ibm.watson.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.visual_recognition.v3.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import java.io.File;

@RestController
@RequestMapping(path = "/image")
public class ImageController {
    private static final Logger LOG = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(path = "/recognition", method = RequestMethod.GET)
    public String test(
            @RequestParam(name = "img_path", defaultValue = "/Users/NakamuraTsukasa/Desktop/633.png") String img_path,
            @RequestParam(name = "img_url", defaultValue = "https://upload.wikimedia.org/wikipedia/commons/2/2a/Jewelkatz_Romeo_Of_Stalker-Bars.jpg") String img_url
    ) throws FileNotFoundException {
        //String url = "https://zukan.pokemon.co.jp/zukan-api/up/images/index/94b9fb82b38847b83a8041e9a78989ce.png";

        IamAuthenticator authenticator = new IamAuthenticator("8nwN0eKp7eZ73So4DLdPndp_yv-vtlI27pN1wK2TjVg1");
        VisualRecognition visualRecognition = new VisualRecognition("2019-07-12", authenticator);

        ClassifyOptions options = new ClassifyOptions.Builder()
                .url(img_url)
                //.imagesFile(new File(img_path))
                .build();
        ClassifiedImages result = visualRecognition.classify(options).execute().getResult();
        //System.out.println(result);

        List<ClassifiedImage> images = result.getImages();

        ClassifiedImage image = images.get(0);
        ClassifierResult r = image.getClassifiers().get(0);
        ClassResult c = r.getClasses().get(0);

        return c.getXClass();

    }
}