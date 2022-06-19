package jwt;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

import model.object.NguoiDung;

public class JwtTokenCungCap{
    static final String JWT_SECRET = "webgame";
    static final long JWT_EXPIRATION = 604800000L;
    static public String generateToken(NguoiDung user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        return Jwts.builder()
                   .setSubject(user.getTen())
                   .setIssuedAt(now)
                   .setExpiration(expiryDate)
                   .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                   .compact();
    }

    static public String layTenTuJWT(String token) {
        Claims claims = Jwts.parser()
                            .setSigningKey(JWT_SECRET)
                            .parseClaimsJws(token)
                            .getBody();

        return claims.getSubject();
    }

    static public boolean thoaManToken(String authToken, HttpServletResponse response) throws IOException {
        if(authToken ==  null||authToken.isEmpty()){
            return false;
        }
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            System.out.println("Jwt không hợp lệ");
            response.setStatus(403);
        } catch (ExpiredJwtException ex) {
            System.out.println("Jwt hết hạn");
            response.setStatus(403);
        } catch (UnsupportedJwtException ex) {
            System.out.println("Jwt không được hỗ trợ");
            response.setStatus(403);
        } catch (IllegalArgumentException ex) {
            System.out.println("Jwt rỗng");
            response.setStatus(403);
        }
        return false;
    }
}