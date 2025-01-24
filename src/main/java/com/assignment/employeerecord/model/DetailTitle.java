package com.assignment.employeerecord.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class DetailTitle {
   
   @NotNull
   private Integer empNo;
   
   @NotNull
   private String title;
   
   @NotNull
   private LocalDate fromDate;
   
   @NotNull
   private LocalDate toDate;
}
