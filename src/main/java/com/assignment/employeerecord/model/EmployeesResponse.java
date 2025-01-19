package com.assignment.employeerecord.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@Getter
@Setter
@Builder
public class EmployeesResponse {
   
   private Integer empNo;
   
   private String fullName;
   
   private BigDecimal age;
   
   private String title;
   
   private String department;
   
   private Date hireDate;
   
   private Integer salary;
}
