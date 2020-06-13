package rbs.wg.WorkoutGenerator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import rbs.wg.WorkoutGenerator.dto.AuthenticationRequestDto;
import rbs.wg.WorkoutGenerator.dto.AuthenticationResponseDto;
import rbs.wg.WorkoutGenerator.exception.ApiBadRequestException;
import rbs.wg.WorkoutGenerator.exception.ApiNotFoundException;
import rbs.wg.WorkoutGenerator.model.Authority;
import rbs.wg.WorkoutGenerator.security.JwtUtils;
import rbs.wg.WorkoutGenerator.security.UserAndAdminDetailsService;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserAndAdminDetailsService userAndAdminDetailsService;

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (BadCredentialsException ex) {
            throw new ApiBadRequestException("Incorrect username or password");
        }

        final UserDetails userDetails = userAndAdminDetailsService.loadUserByUsername(authRequest.getEmail());
        final String token = jwtUtils.generateToken(userDetails);

        Authority authority = (Authority) userDetails
                .getAuthorities()
                .stream()
                .findFirst()
                .orElseThrow(() -> new ApiNotFoundException("Failed to find user role"));

        return new AuthenticationResponseDto(token, authority.getRole());
    }


}
