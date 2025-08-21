
package com.amal.records.domain;
import jakarta.persistence.*;
@Entity @Table(name="medical_records")
public class MedicalRecord {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  private String name;
  @Column(columnDefinition="TEXT") private String description;
  public Long getId(){return id;} public void setId(Long id){this.id=id;}
  public String getName(){return name;} public void setName(String n){this.name=n;}
  public String getDescription(){return description;} public void setDescription(String d){this.description=d;}
}
