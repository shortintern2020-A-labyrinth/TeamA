package com.example.demo.dao;

import com.example.demo.entity.Emolog;
import com.example.demo.entity.Sample;
import org.seasar.doma.BatchInsert;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Dao
@ConfigAutowireable
public interface EmologDao {

    @Select
    List<Emolog> selectAll(int user, int friend);

    @Insert
    int insert(Emolog e);

    @BatchInsert
    int[] insertAll(List<Emolog> e);
}