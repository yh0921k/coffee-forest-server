package com.coffeeforest.commons.jwt;

import com.coffeeforest.commons.exception.ExceptionState;
import com.coffeeforest.commons.exception.detail.JwtRuntimeException;
import io.jsonwebtoken.*;
import lombok.Getter;

import java.security.Key;
import java.util.Map;
import java.util.Optional;

public class AuthToken {

  @Getter
  private String value;
  private Key key;

  public AuthToken(Map<String, Object> payload, Key key) {
    this.key = key;
    this.value = createJwtAuthToken(payload).get();
  }

  public AuthToken(String value, Key key) {
    this.value = value;
    this.key = key;
  }

  public void validate() {
    if (getData() == null) {
      throw new JwtRuntimeException(ExceptionState.INVALID_TOKEN, "Token Data Empty");
    }
  }

  public Claims getData() {
    try {
      return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(value).getBody();
    } catch (SecurityException e) {
      throw new JwtRuntimeException(ExceptionState.INVALID_TOKEN, "Invalid JWT signature");
    } catch (MalformedJwtException e) {
      throw new JwtRuntimeException(ExceptionState.INVALID_TOKEN, "Invalid JWT token");
    } catch (ExpiredJwtException e) {
      throw new JwtRuntimeException(ExceptionState.INVALID_TOKEN, "Expired JWT token");
    } catch (UnsupportedJwtException e) {
      throw new JwtRuntimeException(ExceptionState.INVALID_TOKEN, "Unsupported JWT token");
    } catch (IllegalArgumentException e) {
      throw new JwtRuntimeException(
          ExceptionState.INVALID_TOKEN, "JWT token compact of handler are invalid");
    }
  }

  private Optional<String> createJwtAuthToken(Map<String, Object> payload) {
    String token =
        Jwts.builder()
            .setHeader(createHeaders())
            .setClaims(payload)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();

    return Optional.ofNullable(token);
  }

  private Map<String, Object> createHeaders() {
    return Map.of("typ", "JWT", "alg", "HS256");
  }
}
