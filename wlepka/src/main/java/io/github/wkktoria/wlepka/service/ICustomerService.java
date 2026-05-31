package io.github.wkktoria.wlepka.service;

import io.github.wkktoria.wlepka.dto.request.RegisterRequestDto;
import io.github.wkktoria.wlepka.dto.response.RegisterResponseDto;

public interface ICustomerService {

    RegisterResponseDto registerUser(final RegisterRequestDto registerRequestDto);

}
