package rbs.wg.WorkoutGenerator.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtUtils {

    @Value("${security.jwt.secret}")
    private String SECRET;

    public String generateToken(UserDetails userDetails) {

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public String extractUsername(String jwtToken) {

        return Jwts
                .parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(jwtToken)
                .getBody()
                .getSubject();

    }

    private boolean isTokenExpired(String token) {
        return false; // TODO: check if expired
    }

    public boolean validateToken(String token, UserDetails userDetails) {

        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
