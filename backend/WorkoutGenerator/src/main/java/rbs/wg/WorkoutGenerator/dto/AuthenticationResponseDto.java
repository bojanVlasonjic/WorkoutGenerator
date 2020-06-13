package rbs.wg.WorkoutGenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationResponseDto {

    private final String jwt;
    private final String role;


}
