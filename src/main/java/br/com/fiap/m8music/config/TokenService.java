package br.com.fiap.m8music.config;

import br.com.fiap.m8music.domain.Cantor;
import br.com.fiap.m8music.domain.Cliente;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

//    @Value("${secret}")
    private String secret = "nflmajxzghywuorhawgksvducpyyumbkxfoysmcrodndxsloostqrwqrbiovyyxqzzviapnugpnmcaleempqhlzubttezzyqalppxsngbmxkhywoojcaewxhvbeihhffrlyavgdrlyncvomjgrcpaiqyfmrdzjlnpntgqnzcvdcaadnwceihcxurcqevbzpfnewpzfggzpgifkgawbnguwbzcvofchgizztcnzufzojsksxhvrjeqtvixjtywtmirgoxuqpulqpxltgxnmknjeirrdeczadubzaickirytxdgdfpqdimkbbswrcdvmm";

    public String generateToken(Cantor cantor) {
        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("auth-api")
                    .withExpiresAt(genExpirationDateCantor())
                    .withSubject(cantor.getEmail())
                    .sign(algorithm);

        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro enquanto gerava o token", e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (Exception e) {
            return null;
        }
    }

    public String generateGuestToken(Cliente cliente) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(cliente.getId().toString())
                    .withExpiresAt(genExpirationDateGuest())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro enquanto gerava o token", e);
        }
    }


    private Instant genExpirationDateCantor() {
        return LocalDateTime.now().plusDays(6).toInstant(ZoneOffset.UTC);
    }

    private Instant genExpirationDateGuest() {
        return LocalDateTime.now().plusHours(6).toInstant(ZoneOffset.UTC);
    }

}
