
package com.amal.userservice;
import org.springframework.boot.SpringApplication; import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*; import java.util.Map;
@SpringBootApplication @RestController @RequestMapping("/api/users")
public class UserServiceApplication {
  public static void main(String[] args){ SpringApplication.run(UserServiceApplication.class, args); }
  @GetMapping("/health") public Map<String,String> health(){ return Map.of("status","UP"); }
}
