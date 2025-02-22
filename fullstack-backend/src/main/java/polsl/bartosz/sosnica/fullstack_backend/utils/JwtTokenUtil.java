package polsl.bartosz.sosnica.fullstack_backend.utils;

import java.sql.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtil {

    private static final String SECRET_KEY = "M2lU7lBrl90389ojhkFuRX2HqjmIHT9aH2dTES4cVFwAV33ZH3pAiXfWCzc9Wv4z";

    @SuppressWarnings("deprecation")
    static public String generateToken(String user) {
        return Jwts.builder()
                .setSubject(user)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
