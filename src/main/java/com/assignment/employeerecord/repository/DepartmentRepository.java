package com.assignment.employeerecord.repository;

import com.assignment.employeerecord.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
   Department findByDeptName(String name);
}
