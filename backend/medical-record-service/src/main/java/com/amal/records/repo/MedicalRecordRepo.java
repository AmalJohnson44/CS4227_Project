
package com.amal.records.repo;
import com.amal.records.domain.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MedicalRecordRepo extends JpaRepository<MedicalRecord,Long>{}
