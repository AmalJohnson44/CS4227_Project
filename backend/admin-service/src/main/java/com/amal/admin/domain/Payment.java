
package com.amal.admin.domain;
import jakarta.persistence.*; import java.time.Instant;
@Entity @Table(name="payments")
public class Payment {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  private String patient; private Double amount; private String status; private Instant createdAt;
  @PrePersist public void pre(){ if(status==null) status="PAID"; if(createdAt==null) createdAt=Instant.now(); }
  public Long getId(){return id;} public void setId(Long id){this.id=id;}
  public String getPatient(){return patient;} public void setPatient(String p){this.patient=p;}
  public Double getAmount(){return amount;} public void setAmount(Double a){this.amount=a;}
  public String getStatus(){return status;} public void setStatus(String s){this.status=s;}
  public Instant getCreatedAt(){return createdAt;} public void setCreatedAt(Instant t){this.createdAt=t;}
}
