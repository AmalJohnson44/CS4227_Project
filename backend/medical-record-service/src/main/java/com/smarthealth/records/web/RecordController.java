
package com.smarthealth.records.web;
import com.smarthealth.records.repo.RecordRepo;
import com.smarthealth.records.domain.MedicalRecord;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api/records")
public class RecordController {
  private final RecordRepo repo; public RecordController(RecordRepo r){this.repo=r;}
  @GetMapping public List<MedicalRecord> all(){ return repo.all(); }
  @PostMapping("/add") public MedicalRecord add(@RequestBody MedicalRecord r){ return repo.save(r); }
  @PutMapping("/{id}/amend") public MedicalRecord amend(@PathVariable String id, @RequestBody Map<String,String> p){ MedicalRecord r=repo.byId(id).orElseThrow(); if(p.containsKey("description")) r.setDescription(p.get("description")); return repo.save(r); }
}
