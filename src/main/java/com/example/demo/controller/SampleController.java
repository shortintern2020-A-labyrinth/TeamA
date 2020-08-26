package com.example.demo.controller;

import com.example.demo.entity.Emolog;
import com.example.demo.entity.Sample;
import com.example.demo.service.EmologService;
import com.example.demo.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@RestController
@RequestMapping(path = "/sample")
public class SampleController {

    @Autowired
    SampleService service;

    @RequestMapping(value="/users",method=RequestMethod.GET)
    public List<Sample> selectAll() {
        return service.selectAll(1);
    }

    @RequestMapping(value="/users/add",method=RequestMethod.GET)
    public List<Sample> insert() {
        return service.insert();
    }

}