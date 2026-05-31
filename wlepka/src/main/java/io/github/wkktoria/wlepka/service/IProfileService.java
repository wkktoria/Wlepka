package io.github.wkktoria.wlepka.service;

import io.github.wkktoria.wlepka.dto.request.UpdateProfileRequestDto;
import io.github.wkktoria.wlepka.dto.response.ProfileResponseDto;

public interface IProfileService {

    ProfileResponseDto getProfile();

    ProfileResponseDto updateProfile(final UpdateProfileRequestDto updateProfileRequestDto);

}
