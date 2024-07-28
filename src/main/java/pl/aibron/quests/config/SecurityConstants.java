package pl.aibron.quests.config;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class SecurityConstants {

    public static final long JWT_EXPIRATION = 70000;
    public static final String JWT_SECRET = "secretsecretsecretsecretsecretsecretsecretsecretsecretsecretsecretsecretsecret";

    public static final SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWT_SECRET));


}
