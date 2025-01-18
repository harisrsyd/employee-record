package com.assignment.employeerecord.entity.compositekey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalaryId implements Serializable {
   
   private Integer empNo;
   
   private LocalDate fromDate;
   
   @Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      if (!(obj instanceof SalaryId salaryId)) return false;
      return Objects.equals(empNo, salaryId.empNo) &&
          Objects.equals(fromDate, salaryId.fromDate);
   }
   
   @Override
   public int hashCode() {
      return Objects.hash(empNo, fromDate);
   }
}
