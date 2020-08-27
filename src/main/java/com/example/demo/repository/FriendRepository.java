package com.example.demo.repository;

import com.example.demo.dao.FriendDao;
import com.example.demo.entity.Friend;
import org.seasar.doma.BatchUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FriendRepository {

    @Autowired
    FriendDao dao;

    public List<Friend> selectAll(int user) { // (4)
        return dao.selectAll(user);
    }

    public List<Friend> selectAllRecord(){
        return dao.selectAllRecord();
    }

    @BatchUpdate
    public int[] updateAll(List<Friend> friends){
        return dao.updateAll(friends);
    }
}