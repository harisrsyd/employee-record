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
public class TitleId implements Serializable {
   
   private Integer empNo;
   
   private String title;
   
   private LocalDate fromDate;
   
   @Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      if (!(obj instanceof TitleId titleId)) return false;
      return Objects.equals(empNo, titleId.empNo) &&
          Objects.equals(title, titleId.title) &&
          Objects.equals(fromDate, titleId.fromDate);
   }
   
   @Override
   public int hashCode() {
      return Objects.hash(empNo, title, fromDate);
   }
}
