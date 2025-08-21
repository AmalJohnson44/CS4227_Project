
package com.amal.notificationservice;
import org.springframework.boot.SpringApplication; import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*; import java.util.Map;
@SpringBootApplication @RestController @RequestMapping("/api/notifications")
public class NotificationServiceApplication {
  public static void main(String[] args){ SpringApplication.run(NotificationServiceApplication.class, args); }
  @GetMapping("/health") public Map<String,String> health(){ return Map.of("status","UP"); }
}
