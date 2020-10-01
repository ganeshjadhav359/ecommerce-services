package com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.security;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/auth/registrations";
    public static final String HASHING_ALGORITHM = "SHA-256";
}
