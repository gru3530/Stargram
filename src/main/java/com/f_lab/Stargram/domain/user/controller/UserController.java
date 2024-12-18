package com.f_lab.Stargram.domain.user.controller;

import com.f_lab.Stargram.domain.user.model.Profile;
import com.f_lab.Stargram.domain.user.model.UpdateProfileRequestDto;
import com.f_lab.Stargram.domain.user.service.UserService;
import com.f_lab.Stargram.model.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/{userId}/profile")
    public ResponseEntity<ApiResponse<?>> updateProfile(
            @PathVariable long userId,
            @RequestBody UpdateProfileRequestDto updateProfileRequestDto) {
        userService.updateProfile(userId, updateProfileRequestDto);
        return ResponseEntity.ok(ApiResponse.success("Profile updated successfully"));
    }

    @GetMapping("/{userId}/profile")
    public ResponseEntity<ApiResponse<Profile>> getProfile(@PathVariable long userId) {
        Profile profile = userService.getProfileInfo(userId);
        return ResponseEntity.ok(ApiResponse.success(profile));
    }

    @PatchMapping("/{userId}/profile-image")
    public ResponseEntity<ApiResponse<?>> updateProfileImage(
            @PathVariable long userId,
            @RequestParam String profileImgUrl) {
        boolean success = userService.updateProfileImage(userId, profileImgUrl);
        return ResponseEntity.ok(ApiResponse.success(success ? "Profile image updated successfully" : "Update failed"));
    }

    @PostMapping("/batch")
    public ResponseEntity<ApiResponse<?>> getUsersByIds(@RequestBody List<Long> userIds) {
        var profiles = userService.getUsersByUserIds(userIds);
        return ResponseEntity.ok(ApiResponse.success(profiles));
    }

    @PatchMapping("/{userId}/change-password")
    public ResponseEntity<ApiResponse<?>> changePassword(
            @PathVariable long userId,
            @RequestParam String newPassword) {
        userService.changePassword(userId, newPassword);
        return ResponseEntity.ok(ApiResponse.success("Password changed successfully"));
    }
}
