package polsl.bartosz.sosnica.fullstack_backend.utils;

import java.sql.Date;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;

/**
 * Utility class for generating JSON Web Tokens (JWT).
 * 
 * <p>
 * This class provides a method to generate a JWT for a given user, using
 * a secret key and HS256 signing algorithm.
 * </p>
 */
@Component
public class JwtTokenUtil {

    /**
     * Secret key used for signing the JWT.
     * <p>
     * This key should be stored securely and not be hardcoded in production.
     * </p>
     */
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    /**
     * Generates a JWT token for the specified user.
     * 
     * @param user The username or identifier of the user for whom the token is
     *             being generated.
     * @return A JWT token as a string.
     * 
     * @deprecated The `Date` constructor used here is deprecated. Consider using
     *             `java.util.Date` or another approach to avoid deprecation
     *             warnings.
     */
    public String generateToken(String user) {
        return Jwts.builder()
                .setSubject(user)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
