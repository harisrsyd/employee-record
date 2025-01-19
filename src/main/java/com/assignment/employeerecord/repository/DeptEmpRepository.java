package com.assignment.employeerecord.repository;

import com.assignment.employeerecord.entity.DeptEmp;
import com.assignment.employeerecord.entity.Employee;
import com.assignment.employeerecord.entity.compositekey.DeptId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DeptEmpRepository extends JpaRepository<DeptEmp, DeptId> {
   
   @Query(value = "SELECT * FROM dept_emp WHERE emp_no = ?1 AND to_date = ?2", nativeQuery = true)
   DeptEmp findByEmpNoLatest(Employee emp, LocalDate toDate);
}
