package com.assignment.employeerecord.repository;

import com.assignment.employeerecord.entity.DeptEmp;
import com.assignment.employeerecord.entity.Employee;
import com.assignment.employeerecord.entity.compositekey.DeptId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DeptEmpRepository extends JpaRepository<DeptEmp, DeptId> {
   
   DeptEmp findByEmpNoAndToDateMatches(Employee emp, LocalDate toDate);
}
