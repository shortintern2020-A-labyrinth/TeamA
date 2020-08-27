package com.example.demo.service;

import com.example.demo.entity.Emolog;
import com.example.demo.entity.Friend;
import com.example.demo.repository.EmologRepository;
import com.example.demo.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class EmologService {

    @Autowired
    EmologRepository emologRepository;

    @Autowired
    FriendRepository friendRepository;


    public Emolog selectById(int friendemologId){
        return repository.selectById(friendemologId);
    }

    public Emolog selectByDay(Emolog friendEmolog){
        return repository.selectByDay(friendEmolog.getFriendid(), friendEmolog.getUserid(), friendEmolog.getCreated_at());
    }

    public List<Emolog> selectAll(int user, int friend){
        return emologRepository.selectAll(user, friend);
    }

    public List<Emolog> insert(int user, int friend, String contents) throws ParseException {
        Emolog e = new Emolog();
        e.setId((int)Calendar.getInstance().getTimeInMillis());
        e.setUserid(user);
        e.setFriendid(friend);
        LocalDateTime created_at = LocalDateTime.now();
        e.setCreated_at(created_at);
        e.setContents(contents);
        emologRepository.insert(e);
        return emologRepository.selectAll(user, friend);
    }

    public void createEmolog() throws ParseException {
        List<Friend> friends = friendRepository.selectAllRecord();

        //TODO　ここにEmolog生成処理記述
        List<Emolog> newEmologs = new ArrayList<Emolog>();
        LocalDateTime created_at = LocalDateTime.now();
        int count = 0;
        for (Friend friend : friends) {
            String emologStr = "/*[ ここにEmologを生成する処理 ]*/";
            friend.setLatestemolog(emologStr);
            friend.setUpdated_at(created_at);
            Emolog e = new Emolog();
            e.setContents(emologStr);
            e.setFriendid(friend.getId());
            e.setUserid(friend.getUserid());
            e.setCreated_at(created_at);
            e.setId((int) Calendar.getInstance().getTimeInMillis() + count);
            newEmologs.add(e);
            count++;
        }

        friendRepository.updateAll(friends);
        emologRepository.insertAll(newEmologs);

    }

}