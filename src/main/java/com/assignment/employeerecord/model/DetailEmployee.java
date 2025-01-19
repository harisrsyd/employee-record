package com.assignment.employeerecord.model;

import com.assignment.employeerecord.entity.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class DetailEmployee {
   
   @NotNull
   private Integer empNo;
   
   @NotNull
   private LocalDate birthDate;
   
   @NotBlank
   @Size(max = 14)
   private String firstName;
   
   @NotBlank
   @Size(max = 16)
   private String lastName;
   
   @NotNull
   @Enumerated(EnumType.STRING)
   private Gender gender;
   
   @NotNull
   private LocalDate hireDate;
}
