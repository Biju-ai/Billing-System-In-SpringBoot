package inventroy.Billing.system.Billing.System.services.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {


    private String SecretKey = "9ded1ce629ef90fc8d05a0060a05135281ed687247d3ba1e32eb3e8a6fb4199dae722d1e10a98f99d22e1f157444e9f748f606d6f0b36a815b3f53facabfc9f881fa433bcf5f9e0a9a0e0d72e2c7863515de38b38ddb887d445e010d9e81844fff235d1677a18cab8ade7f1eaad617dedb9f7643c73c375e029532140cdbbde255e00ecaa419cd8840e50e0648823931cf33802b66e58f822e8483bca3b8e38659f6ec9155ffd2111c51bde10d7b088960af13757dc5d627065e1c7a6c1bded0f45f352c5a278517b77698ee9b67b01885a964103013f382e03cbcd0baf8c282311c3c951b6aa7faeef9deeb0afc089942c656cfc157af04af41d463c856a6ce";


    public String generateToken(UserDetails user) {
        List<String> role =user.getAuthorities().stream()
                .map(item->item.getAuthority()).collect(Collectors
                        .toList());


//        Map<String,Object> claims = new HashMap<>();


        return Jwts.builder()
                .claims()
                .add("role",role)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+(1000*60*30*60)))
                .and()
                .signWith(getKey())
                .compact();
    }
    //

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);


    }
    public String extractRole(String token) {
        Object rolesObject = extractAllClaims(token).get("role");

        if (rolesObject instanceof List<?>) {
            List<?> rolesList = (List<?>) rolesObject;
            if (!rolesList.isEmpty()) {
                return rolesList.get(0).toString(); // e.g., "ROLE_ADMIN"
            }
        }

        return "ROLE_UNKNOWN";
    }



//    private <T> T extractClaim(...)
//This is a generic method — it can return any type T.

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //    This method is a generic utility function used in a
//    JWT (JSON Web Token) service class to
//    extract any specific claim (like email, expiration, etc.) from the token.



    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }


    public boolean validateToken(String token, UserDetails userDetail) {
        final String username = extractUsername(token);
        return (username.equals(userDetail.getUsername()) && !isTokenExpired(token) );
    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }


}
