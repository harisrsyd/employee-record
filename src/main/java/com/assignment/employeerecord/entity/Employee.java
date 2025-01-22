package com.assignment.employeerecord.entity;

//import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "employees")
public class Employee {
   
   @Id
   @Column(nullable = false, unique = true)
   private Integer empNo;
   
   @Column(nullable = false)
   private LocalDate birthDate;
   
   @Column(nullable = false, length = 14)
   private String firstName;
   
   @Column(nullable = false, length = 16)
   private String lastName;
   
   @Enumerated(EnumType.STRING)
   @Column(columnDefinition = "gender")
//   @Type(PostgreSQLEnumType.class)
   private Gender gender;
   
   @Column(nullable = false)
   private LocalDate hireDate;
   
   @Column(nullable = false, name = "is_deleted")
   private boolean isDeleted = false;
}
