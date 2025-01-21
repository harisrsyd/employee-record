package com.assignment.employeerecord.model;

import com.assignment.employeerecord.entity.Employee;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class DetailSalary {
   
   @NotNull
   private Integer empNo;
   
   @NotNull
   private Integer salary;
   
   @NotNull
   private LocalDate fromDate;
   
   @NotNull
   private LocalDate toDate;
}
