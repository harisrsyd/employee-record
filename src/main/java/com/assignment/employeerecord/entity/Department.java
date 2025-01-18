package com.assignment.employeerecord.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "departments")
public class Department {
   
   @Id
   @Column(length = 4, nullable = false)
   private String deptNo;
   
   @Column(nullable = false, unique = true, length = 40)
   private String deptName;
}
