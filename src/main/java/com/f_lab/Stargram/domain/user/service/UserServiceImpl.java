package com.f_lab.Stargram.domain.user.service;

import com.f_lab.Stargram.domain.user.repository.UserRepository;
import com.f_lab.Stargram.domain.user.model.User;
import com.f_lab.Stargram.domain.user.model.Profile;
import com.f_lab.Stargram.domain.user.model.UpdateProfileRequestDto;
import com.f_lab.Stargram.common.exception.UserNotFoundException;
import com.f_lab.Stargram.common.exception.InvalidInputException;
import com.f_lab.Stargram.utils.UserRedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRedisUtil userRedisUtil;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    @Override
    public void updateProfile(long userId, UpdateProfileRequestDto updateProfileRequestDto) {
        validateUserId(userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setUserName(updateProfileRequestDto.getUsername());
        user.setEmail(updateProfileRequestDto.getEmail());
        user.setProfileImageUrl(updateProfileRequestDto.getProfileImageUrl());

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public Profile getProfileInfo(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return new Profile(user.getId(), user.getUserName(), user.getEmail(), user.getProfileImageUrl());
    }

    @Transactional
    @Override
    public boolean updateProfileImage(long userId, String profileImgUrl) {
        validateUserId(userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setProfileImageUrl(profileImgUrl);
        userRepository.save(user);
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Profile> getUsersByUserIds(List<Long> userIds) {
        Set<Profile> cachedProfiles = userRedisUtil.getUsers(userIds);

        Set<Long> userIdsNotInCache = userIds.stream()
                .filter(userId -> cachedProfiles.stream().noneMatch(profile -> profile.getUserId().equals(userId)))
                .collect(Collectors.toSet());

        if (!userIdsNotInCache.isEmpty()) {
            Set<User> usersFromDb = userRepository.findByIdIn(userIdsNotInCache);

            Set<Profile> profilesFromDb = usersFromDb.stream()
                    .map(user -> new Profile(user.getId(), user.getUserName(), user.getEmail(), user.getProfileImageUrl()))
                    .collect(Collectors.toSet());

            cachedProfiles.addAll(profilesFromDb);

            userRedisUtil.saveAll(profilesFromDb);
        }

        return cachedProfiles;
    }

    private void validateUserId(long userId) {
        if (userId <= 0) {
            throw new InvalidInputException("Invalid user ID");
        }
    }

    @Transactional
    @Override
    public void changePassword(long userId, String newPassword) {
        validateUserId(userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);

        userRepository.save(user);
    }
}