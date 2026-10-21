package com.ispan.eeit69.service.member;

import java.util.List;


import com.ispan.eeit69.model.Friends;
import com.ispan.eeit69.model.FriendsMessages;

public interface FriendService {

    // 獲取所有朋友
    List<Friends> getAllFriends();

    // 根據ID獲取朋友
    Friends getFriendById(Integer id);

    // 儲存朋友

    // 刪除朋友
    void deleteFriend(Integer id);
    
    List<Friends> getFriendsByuserID1(String userID1);

	

	List<Friends> getFriendsByuserID1(int userID1);

	void saveFriend(Friends friend);

	
}
