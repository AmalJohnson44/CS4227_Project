
package com.smarthealth.records.repo;
import com.smarthealth.records.domain.MedicalRecord;
import org.springframework.stereotype.Repository;
import java.util.*; import java.util.concurrent.ConcurrentHashMap;
@Repository public class RecordRepo{
  private final Map<String,MedicalRecord> store = new ConcurrentHashMap<>();
  public List<MedicalRecord> all(){ return new ArrayList<>(store.values()); }
  public Optional<MedicalRecord> byId(String id){ return Optional.ofNullable(store.get(id)); }
  public MedicalRecord save(MedicalRecord r){ if(r.getId()==null||r.getId().isBlank()) r.setId(UUID.randomUUID().toString()); store.put(r.getId(), r); return r; }
}
