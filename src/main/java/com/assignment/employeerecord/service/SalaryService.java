package com.assignment.employeerecord.service;

import com.assignment.employeerecord.entity.Employee;
import com.assignment.employeerecord.entity.Salary;
import com.assignment.employeerecord.entity.compositekey.SalaryId;
import com.assignment.employeerecord.model.DetailSalary;
import com.assignment.employeerecord.repository.EmployeeRepository;
import com.assignment.employeerecord.repository.SalaryRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class SalaryService {
   
   private final ValidationService validationService;
   private SalaryRepository salaryRepository;
   
   private EmployeeRepository employeeRepository;
   
   public SalaryService(SalaryRepository salaryRepository, EmployeeRepository employeeRepository, ValidationService validationService) {
      this.salaryRepository = salaryRepository;
      this.employeeRepository = employeeRepository;
      this.validationService = validationService;
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
   public void updateLatestSalary(Employee employee, LocalDate date) {
      Salary salary = salaryRepository.findByEmpNoLatest(employee, LocalDate.of(9999, 1, 1));
      salary.setToDate(date);
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
   
   @Transactional
   public DetailSalary addNewSalary(DetailSalary request) {
      validationService.validate(request);
      Employee employee = employeeRepository.findById(request.getEmpNo())
          .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
      updateLatestSalary(employee, request.getFromDate());
      
      Salary newSalary = new Salary();
      newSalary.setEmpNo(employee);
      newSalary.setSalary(request.getSalary());
      newSalary.setFromDate(request.getFromDate());
      newSalary.setToDate(LocalDate.of(9999, 1, 1));
      salaryRepository.save(newSalary);
      
      return toDetailSalary(newSalary);
   }
   
   private SalaryId getId(DetailSalary request) {
      SalaryId id = new SalaryId();
      id.setEmpNo(request.getEmpNo());
      id.setFromDate(request.getFromDate());
      return id;
   }
   
   @Transactional
   public DetailSalary updateExistingSalary(DetailSalary request) {
      SalaryId id = getId(request);
      Salary salary = salaryRepository.findById(id)
          .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Salary not found"));
      
      salary.setSalary(request.getSalary());
      salaryRepository.save(salary);
      
      return toDetailSalary(salary);
   }
   
   @Transactional
   public void deleteSalary(DetailSalary request) {
      SalaryId id = getId(request);
      Salary salary = salaryRepository.findById(id)
          .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Salary not found"));
      
      Employee employee = employeeRepository.findById(request.getEmpNo())
          .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
      Salary salaryBefore = salaryRepository.findByEmpNoLatest(employee, request.getFromDate());
      salaryBefore.setToDate(LocalDate.of(9999,1,1));
      salaryRepository.save(salaryBefore);
      
      salaryRepository.delete(salary);
   }
}
