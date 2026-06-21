package io.github.wkktoria.wlepka;

import io.github.wkktoria.wlepka.constants.ApplicationConstants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class RoleMapper {

    public String grantedAuthoritiesToCommaSeparatedRoles(final Collection<? extends GrantedAuthority> grantedAuthorities) {
        return grantedAuthorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(ApplicationConstants.ROLE_COMMA_DELIMITER));
    }

}
