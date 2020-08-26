package com.example.demo.dao;

import com.example.demo.entity.Sample;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Dao
@ConfigAutowireable
public interface SampleDao{

    @Select
    List<Sample> selectAll();

    @Insert
    @Transactional
    int insert(Sample s); // (6)
//    @Update
//    int update(Sample s);
//    @Delete
//    int delete(Sample s);
}