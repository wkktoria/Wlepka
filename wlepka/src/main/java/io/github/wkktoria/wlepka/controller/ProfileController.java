package io.github.wkktoria.wlepka.controller;

import io.github.wkktoria.wlepka.dto.request.UpdateProfileRequestDto;
import io.github.wkktoria.wlepka.dto.response.ProfileResponseDto;
import io.github.wkktoria.wlepka.service.IProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/profile")
@RequiredArgsConstructor
class ProfileController {

    private final IProfileService profileService;

    @GetMapping
    ResponseEntity<ProfileResponseDto> getProfile() {
        return ResponseEntity.ok().body(profileService.getProfile());
    }

    @PutMapping
    ResponseEntity<ProfileResponseDto> updateProfile(@Valid @RequestBody final UpdateProfileRequestDto updateProfileRequestDto) {
        return ResponseEntity.ok().body(profileService.updateProfile(updateProfileRequestDto));
    }

}
