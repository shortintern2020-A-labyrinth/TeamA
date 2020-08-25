package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;
import twitter4j.*;

import java.awt.*;
import java.util.List;
import java.util.Map;

import java.applet.Applet;
import java.net.URL;

@RestController
@RequestMapping(path = "/")
public class ImageController extends Applet{
    private static final Logger LOG = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(path = "/image", method = RequestMethod.GET)
    public String test() {
        IamAuthenticator authenticator = new IamAuthenticator("{apikey}");
        VisualRecognition visualRecognition = new VisualRecognition("{version}", authenticator);
        visualRecognition.setServiceUrl("{url}");
    }

}