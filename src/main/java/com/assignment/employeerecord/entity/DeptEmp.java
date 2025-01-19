package com.assignment.employeerecord.entity;

import com.assignment.employeerecord.entity.compositekey.DeptId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@IdClass(DeptId.class)
@Table(name = "dept_emp")
public class DeptEmp {
   
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
