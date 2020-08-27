package com.example.demo.service;

import com.example.demo.entity.Friend;
import com.example.demo.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {

    @Autowired
    FriendRepository repository;

    public List<Friend> selectAll(int user){
        return repository.selectAll(user);
    }

    public Friend select(int userid, int friendid){
        return repository.select(userid, friendid);
    }
}