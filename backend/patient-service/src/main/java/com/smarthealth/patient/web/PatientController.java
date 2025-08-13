
package com.smarthealth.patient.web;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @RequestMapping("/api/patients")
public class PatientController {
  @PostMapping("/book-appointment")
  public Map<String,String> book(@RequestBody Map<String,String> p){ return Map.of("status","REQUESTED"); }
  @PostMapping("/make-payment")
  public Map<String,String> pay(@RequestBody Map<String,String> p){ return Map.of("status","PAID"); }
  @PutMapping("/amend-appointment/{id}")
  public Map<String,String> amend(@PathVariable String id, @RequestBody Map<String,String> p){ return Map.of("status","AMENDED","appointmentId",id); }
  @PostMapping("/cancel-appointment/{id}")
  public Map<String,String> cancel(@PathVariable String id){ return Map.of("status","CANCELED","appointmentId",id); }
}
