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
import twitter4j.QueryResult;
import twitter4j.TwitterException;

import javax.validation.constraints.Null;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Yuta Takayama
 */
@Service
public class EmologService {

    @Autowired
    EmologRepository emologRepository;

    @Autowired
    FriendRepository friendRepository;

//    @Autowired
    EmologOutput emologOutput;


    public Emolog selectById(int friendemologId){
        return emologRepository.selectById(friendemologId);
    }

    public Emolog selectByDay(Emolog friendEmolog){
        return emologRepository.selectByDay(friendEmolog.getFriendid(), friendEmolog.getUserid(), friendEmolog.getCreated_at());
    }

    public List<Emolog> selectAll(int user, int friend){
        return emologRepository.selectAll(user, friend);
    }

    //BEGIN:nakamura
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
    //END:nakamura

    /**
     * @author Yuta Takayama
     */
    public void createEmolog() throws Exception {
        List<Friend> friends = friendRepository.selectAllRecord();

        List<Emolog> newEmologs = new ArrayList<Emolog>();
        LocalDateTime created_at = LocalDateTime.now();
        int count = 0;
        for (Friend friend : friends) {
            try {
                QueryResult result = emologOutput.querySearch(friend.getName(), friend.getLasttweetid());

            List<String> keywords = emologOutput.calcKeywords(result);

            List<String> emojiList = new ArrayList<String>();

            for( String keyword : keywords){
                emojiList.add(EmologOutput.convertEmoji(keyword));
            }

            // emojiList : ["&#128515", "&#128515", "&#128515"] みたいな感じ。
            // emojiListから一つの文字列に直す。
            String emologStr = String.join("", emojiList);

            //////////
            friend.setLatestemolog(emologStr);
            friend.setUpdated_at(created_at);
            friend.setLasttweetid(result.getMaxId());
            Emolog e = new Emolog();
            e.setContents(emologStr);
            e.setFriendid(friend.getId());
            e.setUserid(friend.getUserid());
            e.setCreated_at(created_at);
            e.setId((int) Calendar.getInstance().getTimeInMillis() + count);
//            newEmologs.add(e);
            emologRepository.insert(e);
            count++;

            }
            catch(NullPointerException e){ }
        }

        friendRepository.updateAll(friends);
//        emologRepository.insertAll(newEmologs);


    }

}