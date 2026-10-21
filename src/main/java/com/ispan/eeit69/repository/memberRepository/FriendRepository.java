package com.ispan.eeit69.repository.memberRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ispan.eeit69.model.Friends;

@Repository
public interface FriendRepository extends JpaRepository<Friends, Long> {

    Friends save(Friends friend);

    List<Friends> findByUserID1(Integer userID1);

    void deleteByUserID1AndUserID2(Integer userID1, Integer userID2);

   
}
