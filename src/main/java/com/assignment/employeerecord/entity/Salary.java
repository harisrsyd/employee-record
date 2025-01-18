package com.assignment.employeerecord.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "salaries")
public class Salary {
   
   @Id
   @ManyToOne
   @JoinColumn(name = "emp_no", nullable = false)
   private Employee empNo;
   
   @Column(nullable = false)
   private int salary;
   
   @Id
   @Column(nullable = false)
   private LocalDate fromDate;
   
   @Column(nullable = false)
   private LocalDate toDate;
}
