package com.assignment.employeerecord.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "dept_manager")
public class DeptManager {
   
   @Id
   @ManyToOne
   @JoinColumn(name = "emp_no", nullable = false)
   private Employee empNo;
   
   @Id
   @ManyToOne
   @JoinColumn(name = "dept_no", nullable = false)
   private Department deptNo;
   
   @Column(nullable = false)
   private LocalDate fromDate;
   
   @Column(nullable = false)
   private LocalDate toDate;
}
