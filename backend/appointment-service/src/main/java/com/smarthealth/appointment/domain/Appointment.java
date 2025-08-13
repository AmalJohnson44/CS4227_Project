
package com.smarthealth.appointment.domain;
public class Appointment {
  private String id; private String type; private String surgeryOrHospital; private String doctor; private String patient; private String time; private String date; private String status;
  public String getId(){return id;} public void setId(String id){this.id=id;}
  public String getType(){return type;} public void setType(String type){this.type=type;}
  public String getSurgeryOrHospital(){return surgeryOrHospital;} public void setSurgeryOrHospital(String s){this.surgeryOrHospital=s;}
  public String getDoctor(){return doctor;} public void setDoctor(String d){this.doctor=d;}
  public String getPatient(){return patient;} public void setPatient(String p){this.patient=p;}
  public String getTime(){return time;} public void setTime(String t){this.time=t;}
  public String getDate(){return date;} public void setDate(String d){this.date=d;}
  public String getStatus(){return status;} public void setStatus(String s){this.status=s;}
}
