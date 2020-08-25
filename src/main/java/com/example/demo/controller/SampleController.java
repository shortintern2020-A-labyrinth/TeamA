package com.example.demo.controller;

import com.example.demo.entity.Sample;
import com.example.demo.service.SampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import twitter4j.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path = "sample")
public class SampleController {

    @Autowired
    SampleService service;

    @RequestMapping(value="/users",method=RequestMethod.GET)
    public List<Sample> selectAll() {
        return service.selectAll();
    }

    @RequestMapping(value="/",method=RequestMethod.POST)
    public List<Sample> add() {
        return service.add();
    }

}