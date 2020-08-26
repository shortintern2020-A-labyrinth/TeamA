package com.example.demo.repository;

import com.example.demo.dao.EmologDao;
import com.example.demo.dao.SampleDao;
import com.example.demo.entity.Emolog;
import com.example.demo.entity.Sample;
import org.seasar.doma.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class EmologRepository {

    @Autowired
    EmologDao dao;

    public List<Emolog> selectAll(int user, int friend) { // (4)
        return dao.selectAll(user, friend);
    }
}