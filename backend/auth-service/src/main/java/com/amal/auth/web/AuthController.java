
package com.amal.auth.web;
import com.amal.auth.repo.UserRepo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@RestController @RequestMapping("/api/auth")
public class AuthController {
  private final SecretKey key = Keys.hmacShaKeyFor("supersecretkeysupersecretkeysupersecret".getBytes());
  private final UserRepo users; private final PasswordEncoder encoder;
  public AuthController(UserRepo users, PasswordEncoder encoder){ this.users=users; this.encoder=encoder; }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody Map<String,String> body){
    var username = body.getOrDefault("username","");
    var password = body.getOrDefault("password","");
    var user = users.findByUsername(username).orElse(null);
    if (user==null || !encoder.matches(password, user.getPassword())) {
      return ResponseEntity.status(401).body(Map.of("error","Invalid credentials"));
    }
    var token = Jwts.builder().setSubject(username).claim("role", user.getRole())
      .setIssuedAt(new Date()).setExpiration(Date.from(Instant.now().plusSeconds(3600)))
      .signWith(key, SignatureAlgorithm.HS256).compact();
    return ResponseEntity.ok(Map.of("token", token, "username", username, "role", user.getRole()));
  }

  @GetMapping("/health") public Map<String,String> health(){ return Map.of("status","UP"); }
}
