package com.ispan.eeit69.service.member;

import com.ispan.eeit69.model.Avatar;

public interface AvatarService {
    void saveOrUpdateAvatar(Avatar avatar);
    Avatar findAvatarByUserId(Integer userId);
}
