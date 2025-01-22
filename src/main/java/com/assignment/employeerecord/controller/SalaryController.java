package com.assignment.employeerecord.controller;

import com.assignment.employeerecord.model.DetailSalary;
import com.assignment.employeerecord.model.WebResponse;
import com.assignment.employeerecord.service.SalaryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class SalaryController {
   
   private SalaryService salaryService;
   
   public SalaryController(SalaryService salaryService) {
      this.salaryService = salaryService;
   }
   
   @GetMapping("/salaries/{EmpNo}")
   public WebResponse<List<DetailSalary>> getAllSalariesByEmpNo(@PathVariable Integer EmpNo) {
      List<DetailSalary> salaries = salaryService.getSalaryHistoryByEmpNo(EmpNo);
      return WebResponse.<List<DetailSalary>>builder().status(HttpStatus.OK.value()).data(salaries).build();
   }
   
   @PostMapping("/salaries")
   public WebResponse<DetailSalary> addNewSalary(@RequestBody DetailSalary request) {
      DetailSalary salary = salaryService.addNewSalary(request);
      return WebResponse.<DetailSalary>builder().status(HttpStatus.CREATED.value()).data(salary).build();
   }
   
   @PutMapping("/salaries/updatelatest")
   public WebResponse<DetailSalary> updateLatestExistingSalary(@RequestBody DetailSalary request) {
      DetailSalary salary = salaryService.updateExistingSalary(request);
      return WebResponse.<DetailSalary>builder().status(HttpStatus.OK.value()).data(salary).build();
   }
   
   @DeleteMapping("/salaries/delete")
   public WebResponse<String> deleteSalary(@RequestBody DetailSalary request) {
      salaryService.deleteSalary(request);
      return WebResponse.<String>builder().status(HttpStatus.OK.value()).data("Salary data deleted Successfully").build();
   }
}
