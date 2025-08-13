
package com.smarthealth.appointment.repo;
import com.smarthealth.appointment.domain.Appointment;
import org.springframework.stereotype.Repository;
import java.util.*; import java.util.concurrent.ConcurrentHashMap; import java.util.stream.Collectors;
@Repository public class AppointmentRepo{
  private final Map<String,Appointment> store = new ConcurrentHashMap<>();
  public List<Appointment> findAll(){ return new ArrayList<>(store.values()); }
  public Optional<Appointment> findById(String id){ return Optional.ofNullable(store.get(id)); }
  public Appointment save(Appointment a){ if(a.getId()==null||a.getId().isBlank()) a.setId(UUID.randomUUID().toString()); store.put(a.getId(),a); return a; }
  public void delete(String id){ store.remove(id); }
}
