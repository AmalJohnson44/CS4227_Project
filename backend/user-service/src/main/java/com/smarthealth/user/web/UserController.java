
package com.smarthealth.user.web;
import com.smarthealth.user.repo.UserRepo;
import com.smarthealth.user.domain.User;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @RequestMapping("/api/users")
public class UserController {
  private final UserRepo repo; public UserController(UserRepo r){this.repo=r;}
  @PostMapping("/{id}/change-username") public User changeUserName(@PathVariable int id, @RequestBody Map<String,String> p){
    User u = repo.find(id).orElse(new User(id,"Unknown","unknown",""));
    u.setUsername(p.getOrDefault("username", u.getUsername())); repo.save(u); return u;
  }
  @PostMapping("/{id}/change-password") public Map<String,String> changePassword(@PathVariable int id){ return Map.of("status","OK"); }
}
