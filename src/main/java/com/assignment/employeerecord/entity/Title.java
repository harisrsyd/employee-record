package com.assignment.employeerecord.entity;

import com.assignment.employeerecord.entity.compositekey.TitleId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@IdClass(TitleId.class)
@Table(name = "titles")
public class Title {
   
   @Id
   @ManyToOne
   @JoinColumn(name = "emp_no", nullable = false)
   private Employee empNo;
   
   @Id
   @Column(nullable = false, length = 50)
   private String title;
   
   @Id
   @Column(nullable = false)
   private LocalDate fromDate;
   
   private LocalDate toDate;
}
