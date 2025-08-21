package com.amal.appointment.web;

import com.amal.appointment.domain.Appointment;
import com.amal.appointment.repo.AppointmentRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentRepo repo;

    public AppointmentController(AppointmentRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }

    @GetMapping
    public List<Appointment> all() {
        return repo.findAll();
    }

    @PostMapping
    public Appointment create(@RequestBody Appointment a) {
        if (a.getStatus() == null || a.getStatus().isBlank()) {
            a.setStatus("PENDING");
        }
        return repo.save(a);
    }

    @PostMapping("/{id}/confirm")
    public ResponseEntity<?> confirm(@PathVariable String id) {
        Optional<Appointment> opt = repo.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("error", "Appointment not found", "id", id));
        }
        Appointment a = opt.get();
        a.setStatus("CONFIRMED");
        repo.save(a);
        return ResponseEntity.ok(Map.of("status", "CONFIRMED", "id", id));
    }

    @PutMapping("/{id}/amend")
    public ResponseEntity<?> amend(@PathVariable String id, @RequestBody Appointment p) {
        Optional<Appointment> opt = repo.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("error", "Appointment not found", "id", id));
        }
        Appointment a = opt.get();
        if (p.getTime() != null) a.setTime(p.getTime());
        if (p.getDate() != null) a.setDate(p.getDate());
        if (p.getDoctor() != null) a.setDoctor(p.getDoctor());
        if (p.getPatient() != null) a.setPatient(p.getPatient());
        if (p.getType() != null) a.setType(p.getType());
        if (p.getSurgeryOrHospital() != null) a.setSurgeryOrHospital(p.getSurgeryOrHospital());
        a.setStatus("AMENDED");
        Appointment saved = repo.save(a);
        return ResponseEntity.ok(saved);
    }
    @PostMapping("/confirm")
    public ResponseEntity<?> confirmByQuery(@RequestParam String id) {
        return confirm(id);
    }
    
    @PostMapping(path = "/confirm", consumes = "application/json")
    public ResponseEntity<?> confirmByBody(@RequestBody Map<String, String> body) {
        String id = body.get("id");
        if (id == null || id.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Missing 'id'"));
        }
        return confirm(id);
    }




    @PostMapping("/{id}/cancel")
    public ResponseEntity<?> cancel(@PathVariable String id) {
        Optional<Appointment> opt = repo.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("error", "Appointment not found", "id", id));
        }
        Appointment a = opt.get();
        a.setStatus("CANCELLED");
        repo.save(a);
        return ResponseEntity.ok(Map.of("status", "CANCELLED", "id", id));
    }
}
