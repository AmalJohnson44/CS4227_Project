
package com.smarthealth.auth.web;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
@RestController @RequestMapping("/api/auth")
public class AuthController {
  private final SecretKey key = Keys.hmacShaKeyFor("supersecretkeysupersecretkeysupersecret".getBytes());
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody Map<String,String> payload){
    String username = payload.getOrDefault("username","user");
    String token = Jwts.builder().setSubject(username).setIssuedAt(new Date())
      .setExpiration(Date.from(Instant.now().plusSeconds(3600)))
      .signWith(key, SignatureAlgorithm.HS256).compact();
    return ResponseEntity.ok(Map.of("token", token, "username", username));
  }
  @GetMapping("/health") public Map<String,String> health(){ return Map.of("status","UP"); }
}
