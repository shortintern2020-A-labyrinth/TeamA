package com.example.demo.repository;

import com.example.demo.dao.SampleDao;
import com.example.demo.entity.Sample;
import org.seasar.doma.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SampleRepository{

    @Autowired
    SampleDao dao;

    public List<Sample> selectAll() { // (4)
        return dao.selectAll();
    }
    @Insert
    public int insert(Sample s) {
        return dao.insert(s);
    } // (6)
//    @Update
//    int update(Sample s);
//    @Delete
//    int delete(Sample s);
//    @Select
//    long countByFinished(boolean finished);
}