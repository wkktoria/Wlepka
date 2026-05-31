package io.github.wkktoria.wlepka.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import static io.github.wkktoria.wlepka.constants.ApplicationConstants.JWT_HEADER;
import static io.github.wkktoria.wlepka.constants.ApplicationConstants.JWT_SECRET_DEFAULT_VALUE;
import static io.github.wkktoria.wlepka.constants.ApplicationConstants.JWT_SECRET_KEY;

@RequiredArgsConstructor
public class JwtValidatorFilter extends OncePerRequestFilter {

    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    private final List<String> publicPaths;

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(JWT_HEADER);

        if (authHeader != null) {
            try {
                String jwt = authHeader.substring(7);
                Environment env = getEnvironment();

                String secret = env.getProperty(JWT_SECRET_KEY, JWT_SECRET_DEFAULT_VALUE);
                SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

                Claims claims = Jwts.parser()
                        .verifyWith(secretKey)
                        .build()
                        .parseSignedClaims(jwt)
                        .getPayload();
                String username = String.valueOf(claims.get("email"));

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        username, null, Collections.emptyList()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception ex) {
                throw new BadCredentialsException("Invalid token");
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(final HttpServletRequest request) {
        String path = request.getRequestURI();
        return publicPaths.stream()
                .anyMatch(publicPath -> pathMatcher.match(publicPath, path));
    }

}
