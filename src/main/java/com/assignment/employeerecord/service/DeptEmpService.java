package com.assignment.employeerecord.service;

import com.assignment.employeerecord.entity.Department;
import com.assignment.employeerecord.entity.DeptEmp;
import com.assignment.employeerecord.entity.Employee;
import com.assignment.employeerecord.repository.DeptEmpRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DeptEmpService {
   
   private DeptEmpRepository deptEmpRepository;
   
   public DeptEmpService(DeptEmpRepository deptEmpRepository) {
      this.deptEmpRepository = deptEmpRepository;
   }
   
   @Transactional
   public void addDeptEmp(Employee employee, Department department) {
      DeptEmp deptEmp = new DeptEmp();
      deptEmp.setEmpNo(employee);
      deptEmp.setDeptNo(department);
      deptEmp.setFromDate(employee.getHireDate());
      deptEmp.setToDate(LocalDate.of(9999, 1, 1));
      deptEmpRepository.save(deptEmp);
   }
   
   @Transactional
   public void updateLatestDeptEmp(Employee employee, LocalDate date) {
      DeptEmp deptEmp = deptEmpRepository.findByEmpNoLatest(employee, LocalDate.of(9999, 1, 1));
      deptEmp.setToDate(date);
      deptEmpRepository.save(deptEmp);
   }
   
}
