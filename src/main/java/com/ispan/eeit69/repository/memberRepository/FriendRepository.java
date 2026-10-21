package com.ispan.eeit69.repository.memberRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.eeit69.model.Friends;

@Repository
public interface FriendRepository extends JpaRepository<Friends, Long> {

    Friends save(Friends friend);

    List<Friends> findByUserID1(Integer userID1);

    Friends findFirstByUserID1AndUserID2(Integer userID1, Integer userID2);

    /** 別人寄給我、尚未處理的邀請 */
    List<Friends> findByUserID2AndStatus(Integer userID2, String status);

    @Transactional
    void deleteByUserID1AndUserID2(Integer userID1, Integer userID2);

   
}
