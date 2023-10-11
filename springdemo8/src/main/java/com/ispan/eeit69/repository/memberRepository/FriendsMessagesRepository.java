package com.ispan.eeit69.repository.memberRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ispan.eeit69.model.FriendsMessages;

@Repository
public interface FriendsMessagesRepository extends CrudRepository<FriendsMessages, Integer > {
    List<FriendsMessages> findBySenderUserid(int senderUserid);
    }
