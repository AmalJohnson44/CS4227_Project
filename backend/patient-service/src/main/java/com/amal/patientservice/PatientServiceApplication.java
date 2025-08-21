
package com.amal.patientservice;
import org.springframework.boot.SpringApplication; import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*; import java.util.Map;
@SpringBootApplication @RestController @RequestMapping("/api/patients")
public class PatientServiceApplication {
  public static void main(String[] args){ SpringApplication.run(PatientServiceApplication.class, args); }
  @GetMapping("/health") public Map<String,String> health(){ return Map.of("status","UP"); }
}
