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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        e.setId(13);
        e.setUserid(user);
        e.setFriendid(friend);
        LocalDateTime create_at = LocalDateTime.now();
        e.setCreate_at(create_at);
        e.setContents(contents);
        repository.insert(e);
        return repository.selectAll(user, friend);
    }

}