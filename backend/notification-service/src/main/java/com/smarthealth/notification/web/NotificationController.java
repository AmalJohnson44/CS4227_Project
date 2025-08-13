
package com.smarthealth.notification.web;
import org.springframework.web.bind.annotation.*; import java.util.Map;
@RestController @RequestMapping("/api/notifications")
public class NotificationController {
  @PostMapping("/send") public Map<String,String> send(@RequestBody Map<String,String> p){ return Map.of("status","SENT","receiver",p.getOrDefault("receiver","")); }
}
