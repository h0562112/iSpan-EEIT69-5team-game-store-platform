package com.ispan.eeit69.service.Impl.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ispan.eeit69.model.Avatar;
import com.ispan.eeit69.repository.memberRepository.AvatarRepository;
import com.ispan.eeit69.service.member.AvatarService;

@Service
public class AvatarServiceImpl implements AvatarService {

    @Autowired
    private AvatarRepository avatarRepository;  // 假設你有一個 AvatarRepository

    @Override
    public void saveOrUpdateAvatar(Avatar avatar) {
        avatarRepository.save(avatar);
    }

    @Override
    public Avatar findAvatarByUserId(Integer userId) {
        return avatarRepository.findByUserId(userId);
    }
}
