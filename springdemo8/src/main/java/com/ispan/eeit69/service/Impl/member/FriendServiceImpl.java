package com.ispan.eeit69.service.Impl.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.eeit69.model.Friends;
import com.ispan.eeit69.model.FriendsMessages;
import com.ispan.eeit69.repository.memberRepository.FriendRepository;
import com.ispan.eeit69.repository.memberRepository.FriendsMessagesRepository;
import com.ispan.eeit69.service.member.FriendService;

@Service
public class FriendServiceImpl implements FriendService {
	 @Autowired
	private final FriendsMessagesRepository repository;
    private final FriendRepository friendRepository;

    @Autowired
    public FriendServiceImpl(FriendsMessagesRepository repository, FriendRepository friendRepository) {
        this.repository = repository;
        this.friendRepository = friendRepository;
    }

    
    @Transactional
    public void saveMessage(FriendsMessages message) {
        repository.save(message);
    }
    
    @Override
    public List<Friends> getAllFriends() {
        return friendRepository.findAll();
    }

    @Override
    public Friends getFriendById(Integer id) {
        // 將 Integer ID 轉換為 Long
        Long longId = Long.valueOf(id);

        return friendRepository.findById(longId).orElse(null);
    }


    @Override
    public void saveFriend(Friends friend) {
        friendRepository.save(friend);
    }

    @Override
    public void deleteFriend(Integer id) {
        // 將 Integer ID 轉換為 Long
        Long longId = Long.valueOf(id);

        friendRepository.deleteById(longId);
    }

    @Override
    public List<Friends> getFriendsByuserID1(int userID1) {
        // 使用 Spring Data JPA 的方法命名規則
        return friendRepository.findByUserID1(userID1);
    }


	@Override
	public List<Friends> getFriendsByuserID1(String userID1) {
		// TODO Auto-generated method stub
		return null;
	}


}
