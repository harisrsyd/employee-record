package com.assignment.employeerecord.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Getter
@Setter
@Builder
public class EmployeesResponse {
   
   private Integer empNo;
   
   private String fullName;
   
   private Integer age;
   
   private String title;
   
   private String department;
   
   private LocalDate hireDate;
   
   private Integer salary;
}
