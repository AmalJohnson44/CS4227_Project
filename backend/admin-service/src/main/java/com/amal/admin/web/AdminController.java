package com.amal.admin.web;

import com.amal.admin.domain.Payment;
import com.amal.admin.repo.PaymentRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final WebClient appointments = WebClient.builder()
            .baseUrl("http://appointment-service:8095")
            .build();

    private final PaymentRepo payments;

    public AdminController(PaymentRepo payments) {
        this.payments = payments;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }

    // Admin → hop to appointment-service to confirm booking
    @PostMapping("/confirm-booking/{id}")
    public ResponseEntity<?> confirm(@PathVariable String id) {
        try {
            appointments.post()
                    .uri("/api/appointments/{id}/confirm", id)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
            return ResponseEntity.ok(Map.of("status", "CONFIRMED", "id", id));
        } catch (Exception ex) {
            return ResponseEntity.status(502).body(Map.of(
                    "error", "Failed to confirm via appointment-service",
                    "id", id,
                    "details", ex.getMessage()
            ));
        }
    }
// Admin → hop to appointment-service to CANCEL a booking
@PostMapping("/cancel-appointment/{id}")
public ResponseEntity<?> cancel(@PathVariable String id) {
    try {
        appointments.post()
                .uri("/api/appointments/{id}/cancel", id)
                .retrieve()
                .toBodilessEntity()
                .block();
        return ResponseEntity.ok(Map.of("status", "CANCELLED", "id", id));
    } catch (Exception ex) {
        return ResponseEntity.status(502).body(Map.of(
                "error", "Failed to cancel via appointment-service",
                "id", id,
                "details", ex.getMessage()
        ));
    }
}

// (optional) Admin → amend appointment details by forwarding to appointment-service
@PutMapping("/amend-appointment/{id}")
public ResponseEntity<?> amend(@PathVariable String id, @RequestBody Map<String,Object> body) {
    try {
        return appointments.put()
                .uri("/api/appointments/{id}/amend", id)
                .bodyValue(body)
                .retrieve()
                .toEntity(Map.class)
                .block();
    } catch (Exception ex) {
        return ResponseEntity.status(502).body(Map.of(
                "error", "Failed to amend via appointment-service",
                "id", id,
                "details", ex.getMessage()
        ));
    }
}

    // Admin → confirm payment for a patient
    // Example call: POST /api/admin/confirm-payment/patient1?amount=100.00
    @PostMapping("/confirm-payment/{patientId}")
    public ResponseEntity<?> confirmPayment(
            @PathVariable String patientId,
            @RequestParam(name = "amount", required = false, defaultValue = "0") BigDecimal amount
    ) {
        try {
            Payment p = new Payment();
            p.setPatient(patientId);
            p.setAmount(amount == null ? 0.0 : amount.doubleValue());
            p.setStatus("PAID");
            payments.save(p);
            return ResponseEntity.ok(Map.of(
                    "status", "PAID",
                    "patient", patientId,
                    "paymentId", p.getId()
            ));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(Map.of(
                    "error", "Payment save failed",
                    "patient", patientId,
                    "details", ex.getMessage()
            ));
        }
    }
}
