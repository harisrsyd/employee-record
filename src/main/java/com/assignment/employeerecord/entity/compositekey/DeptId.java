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
public class DeptId implements Serializable {
   
   private Integer empNo;
   
   private String deptNo;
   
   @Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      if (!(obj instanceof DeptId deptId)) return false;
      return Objects.equals(empNo, deptId.empNo) &&
          Objects.equals(deptNo, deptId.deptNo);
   }
   
   @Override
   public int hashCode() {
      return Objects.hash(empNo, deptNo);
   }
}
