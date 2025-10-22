package com.devteria.profile.controller;

import com.devteria.profile.dto.request.ProfileCreationRequest;
import com.devteria.profile.dto.response.ProfileResponse;
import com.devteria.profile.service.UserProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserProfileController {
    UserProfileService userProfileService;

    @GetMapping("/users/{id}")
    public ProfileResponse getProfile(@PathVariable String id) {
        return userProfileService.getUserProfileById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    List<ProfileResponse> getAllProfiles() {
        return userProfileService.getAllProfiles();
    }
}
