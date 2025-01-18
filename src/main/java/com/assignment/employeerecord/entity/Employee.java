package com.assignment.employeerecord.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "employees")
public class Employee {
   
   @Id
   @Column(nullable = false, unique = true)
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer empNo;
   
   @Column(nullable = false)
   private LocalDate birthDate;
   
   @Column(nullable = false, length = 14)
   private String firstName;
   
   @Column(nullable = false, length = 16)
   private String lastName;
   
   @Column(nullable = false)
   private Gender gender;
   
   @Column(nullable = false)
   private LocalDate hireDate;
}
