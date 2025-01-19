package com.assignment.employeerecord.repository;

import com.assignment.employeerecord.entity.Employee;
import com.assignment.employeerecord.entity.Salary;
import com.assignment.employeerecord.entity.compositekey.SalaryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, SalaryId> {
   
   @Query(value = "SELECT * FROM salaries WHERE emp_no = ?1 AND to_date = ?2", nativeQuery = true)
   Salary findByEmpNoLatest(Employee emp, LocalDate toDate);
}
