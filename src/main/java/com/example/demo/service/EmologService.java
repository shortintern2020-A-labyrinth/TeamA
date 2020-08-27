package com.example.demo.service;

import com.example.demo.common.EmologOutput;
import com.example.demo.controller.ViewController;
import com.example.demo.entity.Emolog;
import com.example.demo.entity.Friend;
import com.example.demo.repository.EmologRepository;
import com.example.demo.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import twitter4j.TwitterException;

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
        return emologRepository.selectById(friendemologId);
    }

    public Emolog selectByDay(Emolog friendEmolog){
        return emologRepository.selectByDay(friendEmolog.getFriendid(), friendEmolog.getUserid(), friendEmolog.getCreated_at());
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

    public void createEmolog() throws Exception {
        List<Friend> friends = friendRepository.selectAllRecord();

        //TODO　ここにEmolog生成処理記述
        List<Emolog> newEmologs = new ArrayList<Emolog>();
        LocalDateTime created_at = LocalDateTime.now();
        int count = 0;
        for (Friend friend : friends) {
            ////
            List<String> keyword_tweets = new ArrayList<String>();
            List<String> keyword_images = new ArrayList<String>();


            //TODO: 「取得した最新のtweet_idを保存して次のバッチ処理ではそのtweet_id以降を取得」って処理がまだ出来てない。
            keyword_tweets = ViewController.get_NLU_keywords(friend.getName(), );
            keyword_images = ViewController.get_image_keywords(friend.getName(), 100000);

            List<String> emojiList = new ArrayList<String>();

            // 文字列整形の方(テキトーに::をつける方)
            for( String keyword : keyword_tweets){
                emojiList.add(EmologOutput.convertEmoji(keyword));
            }

            for( String keyword : keyword_images) {
                emojiList.add(EmologOutput.convertEmoji(keyword));
            }

            // emojiList : ["&#128515", "&#128515", "&#128515"] みたいな感じ。
            // emojiListから一つの文字列に直す。
            String emologStr = String.join("", emojiList);
            ////
//            String emologStr = "/*[ ここにEmologを生成する処理 ]*/";
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