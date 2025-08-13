
package com.smarthealth.appointment.web;
import com.smarthealth.appointment.domain.Appointment;
import com.smarthealth.appointment.repo.AppointmentRepo;
import org.springframework.web.bind.annotation.*;
import java.util.List; import java.util.Map;
@RestController @RequestMapping("/api/appointments")
public class AppointmentController {
  private final AppointmentRepo repo; public AppointmentController(AppointmentRepo r){this.repo=r;}
  @GetMapping public List<Appointment> all(){ return repo.findAll(); }
  @PostMapping public Appointment create(@RequestBody Appointment a){ a.setStatus("PENDING"); return repo.save(a); }
  @PostMapping("/{id}/confirm") public Appointment confirm(@PathVariable String id){ Appointment a=repo.findById(id).orElseThrow(); a.setStatus("CONFIRMED"); return repo.save(a); }
  @PutMapping("/{id}/amend") public Appointment amend(@PathVariable String id, @RequestBody Map<String,String> p){ Appointment a=repo.findById(id).orElseThrow(); if(p.containsKey("time")) a.setTime(p.get("time")); if(p.containsKey("date")) a.setDate(p.get("date")); a.setStatus("AMENDED"); return repo.save(a); }
  @PostMapping("/{id}/cancel") public Appointment cancel(@PathVariable String id){ Appointment a=repo.findById(id).orElseThrow(); a.setStatus("CANCELED"); return repo.save(a); }
}
