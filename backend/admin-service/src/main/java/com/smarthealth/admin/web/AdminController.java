
package com.smarthealth.admin.web;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @RequestMapping("/api/admin")
public class AdminController {
  @PostMapping("/confirm-booking/{appointmentId}")
  public Map<String,String> confirmBooking(@PathVariable String appointmentId){ return Map.of("status","CONFIRMED","appointmentId",appointmentId); }
  @PostMapping("/confirm-payment/{patientId}")
  public Map<String,String> confirmPayment(@PathVariable String patientId){ return Map.of("status","PAID","patientId",patientId); }
  @PutMapping("/amend-appointment/{appointmentId}")
  public Map<String,String> amend(@PathVariable String appointmentId, @RequestBody Map<String,String> p){ return Map.of("status","AMENDED","appointmentId",appointmentId); }
  @PostMapping("/cancel-appointment/{appointmentId}")
  public Map<String,String> cancel(@PathVariable String appointmentId){ return Map.of("status","CANCELED","appointmentId",appointmentId); }
}
