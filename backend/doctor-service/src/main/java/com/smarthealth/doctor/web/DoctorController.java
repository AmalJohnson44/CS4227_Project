
package com.smarthealth.doctor.web;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @RequestMapping("/api/doctors")
public class DoctorController {
  @PostMapping("/{patientId}/send-referral")
  public Map<String,String> sendReferral(@PathVariable String patientId, @RequestBody Map<String,String> p){
    return Map.of("status","SENT","patientId",patientId,"note", p.getOrDefault("note",""));
  }
  @PutMapping("/update-patient-records/{patientId}")
  public Map<String,String> updateRecords(@PathVariable String patientId, @RequestBody Map<String,String> p){
    return Map.of("status","UPDATED","patientId",patientId);
  }
}
