package com.example.demo.service;

import com.example.demo.entity.Emolog;
import com.example.demo.entity.Sample;
import com.example.demo.repository.EmologRepository;
import com.example.demo.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import com.example.demo.entity.Emolog;

@Service
public class EmologService {

    @Autowired
    EmologRepository repository;

    public List<Emolog> selectAll(int user, int friend){
        return repository.selectAll(user, friend);
    }

    public List<Emolog> insert(int user, int friend, String contents) throws ParseException {
        Emolog e = new Emolog();
        e.setId((int)Calendar.getInstance().getTimeInMillis());
        e.setUserid(user);
        e.setFriendid(friend);
        LocalDateTime created_at = LocalDateTime.now();
        e.setCreated_at(created_at);
        e.setContents(contents);
        repository.insert(e);
        return repository.selectAll(user, friend);
    }

}