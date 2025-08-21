
package com.amal.records.web;
import com.amal.records.domain.MedicalRecord; import com.amal.records.repo.MedicalRecordRepo;
import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/records")
public class RecordController {
  private final MedicalRecordRepo repo; public RecordController(MedicalRecordRepo repo){ this.repo=repo; }
  @GetMapping public List<MedicalRecord> all(){ return repo.findAll(); }
  @PostMapping("/add") public MedicalRecord add(@RequestBody MedicalRecord r){ return repo.save(r); }
  @PutMapping("/{id}/amend") public MedicalRecord amend(@PathVariable Long id, @RequestBody MedicalRecord p){
    return repo.findById(id).map(r->{ if(p.getName()!=null)r.setName(p.getName()); if(p.getDescription()!=null)r.setDescription(p.getDescription()); return repo.save(r);} ).orElseThrow();
  }
}
