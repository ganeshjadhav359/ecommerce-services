package com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.dto.UsernamePasswordDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Date;

import static com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.security.SecurityConstants.*;

public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        super();
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            UsernamePasswordDto user = new ObjectMapper()
                                            .readValue(request.getInputStream(),UsernamePasswordDto.class);
            return authenticationManager.authenticate(
                                    new UsernamePasswordAuthenticationToken(
                                            user.getUsername(),
                                            user.getPassword()
                                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        try {

            System.out.println(authResult.toString());
            SecurityUser user = (SecurityUser) authResult.getPrincipal();
            byte[] key = new HashedKeyGenerator().getHashedKey(SECRET);
            String token = Jwts.builder()
                    .setHeaderParam("typ","JWT")
                    .setSubject(user.getUserId().toString())
                    .claim("Roles",authResult.getAuthorities())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                    .signWith(Keys.hmacShaKeyFor(key))
                    .compact();

            response.addHeader(HEADER_STRING,TOKEN_PREFIX+token);
            try {
                response.getWriter().write(new JSONObject()
                        .put("TOKEN", token)
                        .put("User", user.getUsername())
                        .toString());
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
