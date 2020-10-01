package com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.security.SecurityConstants.HASHING_ALGORITHM;

final public class HashedKeyGenerator {
    private MessageDigest md ;

    public HashedKeyGenerator() throws NoSuchAlgorithmException {
        this.md = MessageDigest.getInstance(HASHING_ALGORITHM);
    }
    public byte[] getHashedKey(String key){
        md.update(key.getBytes(StandardCharsets.UTF_8));
        return md.digest();
    }
}
