package com.assignment.employeerecord.service;

import com.assignment.employeerecord.entity.Department;
import com.assignment.employeerecord.entity.DeptManager;
import com.assignment.employeerecord.entity.Employee;
import com.assignment.employeerecord.repository.DeptManagerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DeptManagerService {
   
   private DeptManagerRepository deptManagerRepository;
   
   public DeptManagerService(DeptManagerRepository deptManagerRepository) {
      this.deptManagerRepository = deptManagerRepository;
   }
   
   @Transactional
   public void addDeptManager(Employee employee, Department department) {
      DeptManager deptManager = new DeptManager();
      deptManager.setEmpNo(employee);
      deptManager.setDeptNo(department);
      deptManager.setFromDate(employee.getHireDate());
      deptManager.setToDate(LocalDate.of(9999, 1,1));
      deptManagerRepository.save(deptManager);
   }
   
   @Transactional
   public void updateLatestDeptManager(Employee employee) {
      DeptManager deptManager = deptManagerRepository.findByEmpNoLatest(employee, LocalDate.of(9999,1,1));
      deptManager.setToDate(LocalDate.now());
      deptManagerRepository.save(deptManager);
   }
}
