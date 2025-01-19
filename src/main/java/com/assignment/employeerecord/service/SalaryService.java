package com.assignment.employeerecord.service;

import com.assignment.employeerecord.entity.Employee;
import com.assignment.employeerecord.entity.Salary;
import com.assignment.employeerecord.repository.SalaryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SalaryService {
   
   private SalaryRepository salaryRepository;
   
   public SalaryService(SalaryRepository salaryRepository) {
      this.salaryRepository = salaryRepository;
   }
   
   @Transactional
   public void addFirstSalary(Employee employee, int amount) {
      Salary salary = new Salary();
      salary.setEmpNo(employee);
      salary.setSalary(amount);
      salary.setFromDate(employee.getHireDate());
      salary.setToDate(LocalDate.of(9999,1,1));
      salaryRepository.save(salary);
   }
   
   @Transactional
   public void updateLatestSalary(Employee employee) {
      Salary salary = salaryRepository.findByEmpNoLatest(employee, LocalDate.of(9999, 1, 1));
      salary.setToDate(LocalDate.now());
      salaryRepository.save(salary);
   }
}
