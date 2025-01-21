package com.assignment.employeerecord.service;

import com.assignment.employeerecord.entity.Employee;
import com.assignment.employeerecord.entity.Salary;
import com.assignment.employeerecord.model.DetailEmployee;
import com.assignment.employeerecord.model.DetailSalary;
import com.assignment.employeerecord.repository.EmployeeRepository;
import com.assignment.employeerecord.repository.SalaryRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SalaryService {
   
   private SalaryRepository salaryRepository;
   
   private EmployeeRepository employeeRepository;
   
   public SalaryService(SalaryRepository salaryRepository, EmployeeRepository employeeRepository) {
      this.salaryRepository = salaryRepository;
      this.employeeRepository = employeeRepository;
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
   
   DetailSalary toDetailSalary(Salary salary) {
      return DetailSalary.builder()
              .empNo(salary.getEmpNo().getEmpNo())
              .salary(salary.getSalary())
              .fromDate(salary.getFromDate())
              .toDate(salary.getToDate())
              .build();
   }
   
   public List<DetailSalary> getSalaryHistoryByEmpNo(Integer empNo) {
      Employee employee = employeeRepository.findById(empNo)
          .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
      List<Salary> salaries = salaryRepository.findSalaryHistoryByEmpNo(employee);
      return salaries.stream().map(this::toDetailSalary).toList();
   }
}
