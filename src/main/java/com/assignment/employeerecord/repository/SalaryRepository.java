package com.assignment.employeerecord.repository;

import com.assignment.employeerecord.entity.Employee;
import com.assignment.employeerecord.entity.Salary;
import com.assignment.employeerecord.entity.compositekey.SalaryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, SalaryId> {
   
   Salary findByEmpNoAndToDateMatches(Employee emp, LocalDate toDate);
}
