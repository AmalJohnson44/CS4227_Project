
package com.amal.appointment.repo;
import com.amal.appointment.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AppointmentRepo extends JpaRepository<Appointment,String>{}
