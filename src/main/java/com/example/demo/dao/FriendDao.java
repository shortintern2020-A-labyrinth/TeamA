package com.example.demo.dao;

import com.example.demo.entity.Emolog;
import com.example.demo.entity.Friend;
import org.seasar.doma.BatchUpdate;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

/**
 * @author Yuta Takayama
 */
@Dao
@ConfigAutowireable
public interface FriendDao {

    @Select
    List<Friend> selectAll(int userid);

    @Select
    List<Friend> selectAllRecord();

    @BatchUpdate
    int[] updateAll(List<Friend> friends);

    @Select
    Friend select(int userid, int friendid);
}