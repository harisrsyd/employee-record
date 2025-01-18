package com.assignment.employeerecord.repository;

import com.assignment.employeerecord.entity.CurrentDeptEmp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentDeptEmpRepository extends JpaRepository<CurrentDeptEmp, Integer> {
}
