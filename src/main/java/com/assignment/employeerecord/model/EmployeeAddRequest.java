package com.assignment.employeerecord.model;

import com.assignment.employeerecord.entity.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeAddRequest {

   @NotBlank
   @Size(max = 14)
   private String firstName;
   
   @NotBlank
   @Size(max = 16)
   private String lastName;
   
   @NotNull
   private LocalDate birthDate;
   
   @NotNull
   private Gender gender;
   
   @NotNull
   private LocalDate hireDate;
   
   @NotBlank
   @Size(max = 50)
   private String title;
   
   @NotBlank
   private String department;
   
   @NotNull
   private Integer salary;
}
