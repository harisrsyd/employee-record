package com.assignment.employeerecord.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

import java.time.LocalDate;

@Entity
@Immutable
@Table(name = "current_dept_emp")
public class CurrentDeptEmp {
   @Id
   private Integer empNo;
   
   private String deptNo;
   
   private LocalDate fromDate;
   
   private LocalDate toDate;
}
