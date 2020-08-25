package com.example.demo.dao;

import com.example.demo.entity.Sample;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;

import java.io.Serializable;
import java.util.List;

@Dao
@ConfigAutowireable
public interface SampleDao{

    @Select
    List<Sample> selectAll(); // (4)
    @Insert
        // (5)
    int create(Sample s); // (6)
    @Update
    int update(Sample s);
    @Delete
    int delete(Sample s);
    @Select
    long countByFinished(boolean finished);

}