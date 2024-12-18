package com.f_lab.Stargram.domain.user.service;

import com.f_lab.Stargram.domain.user.model.Profile;
import com.f_lab.Stargram.domain.user.model.UpdateProfileRequestDto;

import java.util.List;
import java.util.Set;

public interface UserService {

    void updateProfile(long userId, UpdateProfileRequestDto updateProfileRequestDto);

    Profile getProfileInfo(long userId);

    boolean updateProfileImage(long userId, String profileImgUrl);

    Set<Profile> getUsersByUserIds(List<Long> userIds);

    void changePassword(long userId, String newPassword);
}