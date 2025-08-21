
package com.amal.appointment.domain;
import jakarta.persistence.*; import java.time.LocalDate; import java.util.UUID;
@Entity @Table(name="appointments")
public class Appointment {
  @Id @Column(length=36) private String id;
  private String type; private String surgeryOrHospital; private String doctor; private String patient; private String time; private LocalDate date; private String status;
  @PrePersist public void pre(){ if(id==null) id=UUID.randomUUID().toString(); if(status==null) status="PENDING"; }
  // getters/setters omitted for brevity
  public String getId(){return id;} public void setId(String id){this.id=id;}
  public String getType(){return type;} public void setType(String t){this.type=t;}
  public String getSurgeryOrHospital(){return surgeryOrHospital;} public void setSurgeryOrHospital(String s){this.surgeryOrHospital=s;}
  public String getDoctor(){return doctor;} public void setDoctor(String d){this.doctor=d;}
  public String getPatient(){return patient;} public void setPatient(String p){this.patient=p;}
  public String getTime(){return time;} public void setTime(String t){this.time=t;}
  public LocalDate getDate(){return date;} public void setDate(LocalDate d){this.date=d;}
  public String getStatus(){return status;} public void setStatus(String s){this.status=s;}
}
