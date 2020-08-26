package com.example.demo.service;

import com.example.demo.entity.Emolog;
import com.example.demo.entity.Sample;
import com.example.demo.repository.EmologRepository;
import com.example.demo.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmologService {

    @Autowired
    EmologRepository repository;

    public List<Emolog> selectAll(int user, int friend){
        return repository.selectAll(user, friend);
    }
}