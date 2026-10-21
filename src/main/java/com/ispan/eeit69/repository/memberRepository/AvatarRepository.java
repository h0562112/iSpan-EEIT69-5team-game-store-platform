package com.ispan.eeit69.repository.memberRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ispan.eeit69.model.Avatar;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Integer> {
    Avatar findByUserId(Integer userId);
}
