package com.assignment.employeerecord.repository;

import com.assignment.employeerecord.entity.DeptManager;
import com.assignment.employeerecord.entity.compositekey.DeptId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptManagerRepository extends JpaRepository<DeptManager, DeptId> {
}
