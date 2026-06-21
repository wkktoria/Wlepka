package io.github.wkktoria.wlepka.service.impl;

import io.github.wkktoria.wlepka.dto.request.UpdateProfileRequestDto;
import io.github.wkktoria.wlepka.dto.response.ProfileResponseDto;
import io.github.wkktoria.wlepka.entity.Address;
import io.github.wkktoria.wlepka.entity.Customer;
import io.github.wkktoria.wlepka.exception.NotUniqueException;
import io.github.wkktoria.wlepka.repository.CustomerRepository;
import io.github.wkktoria.wlepka.service.IProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements IProfileService {

    private final CustomerRepository customerRepository;

    @Override
    public ProfileResponseDto getProfile() {
        Customer customer = getAuthenticatedCustomer();
        return mapCustomerToProfileResponseDto(customer);
    }

    @Override
    public ProfileResponseDto updateProfile(final UpdateProfileRequestDto updateProfileRequestDto) {
        Customer customer = getAuthenticatedCustomer();
        boolean isEmailUpdated = !customer.getEmail().equalsIgnoreCase(updateProfileRequestDto.email().trim());

        if (isEmailUpdated && customerRepository.existsByEmail(updateProfileRequestDto.email().trim())) {
            throw new NotUniqueException("email", "Istnieje już konto zarejestrowane na podany adres e-mail.");
        }

        boolean isMobileNumberUpdated = !customer.getMobileNumber().equals(updateProfileRequestDto.mobileNumber().trim());
        if (isMobileNumberUpdated && customerRepository.existsByMobileNumber(updateProfileRequestDto.mobileNumber().trim())) {
            throw new NotUniqueException("mobileNumber", "Podany numer telefonu jest już przypisany do innego konta.");
        }

        BeanUtils.copyProperties(updateProfileRequestDto, customer);

        Address address = customer.getAddress();
        if (address == null) {
            address = new Address();
            address.setCustomer(customer);
        }

        address.setStreet(updateProfileRequestDto.street());
        address.setCity(updateProfileRequestDto.city());
        address.setState(updateProfileRequestDto.state());
        address.setPostalCode(updateProfileRequestDto.postalCode());
        address.setCountry(updateProfileRequestDto.country());
        customer.setAddress(address);
        customer = customerRepository.save(customer);

        ProfileResponseDto profileResponseDto = mapCustomerToProfileResponseDto(customer);
        profileResponseDto.setEmailUpdated(isEmailUpdated);

        return profileResponseDto;
    }

    private Customer getAuthenticatedCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private ProfileResponseDto mapCustomerToProfileResponseDto(final Customer customer) {
        return ProfileResponseDto.builder()
                .customerId(customer.getCustomerId())
                .name(customer.getName())
                .email(customer.getEmail())
                .mobileNumber(customer.getMobileNumber())
                .street(customer.getAddress() != null ? customer.getAddress().getStreet() : null)
                .city(customer.getAddress() != null ? customer.getAddress().getCity() : null)
                .state(customer.getAddress() != null ? customer.getAddress().getState() : null)
                .postalCode(customer.getAddress() != null ? customer.getAddress().getPostalCode() : null)
                .country(customer.getAddress() != null ? customer.getAddress().getCountry() : null)
                .build();
    }

}
