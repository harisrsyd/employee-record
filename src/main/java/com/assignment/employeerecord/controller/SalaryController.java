package com.assignment.employeerecord.controller;

import com.assignment.employeerecord.model.DetailSalary;
import com.assignment.employeerecord.model.WebResponse;
import com.assignment.employeerecord.service.SalaryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class SalaryController {
   
   private SalaryService salaryService;
   
   public SalaryController(SalaryService salaryService) {
      this.salaryService = salaryService;
   }
   
   @GetMapping("/salaries")
   public WebResponse<List<DetailSalary>> getAllSalariesByEmpNo(Integer EmpNo) {
      List<DetailSalary> salaries = salaryService.getSalaryHistoryByEmpNo(EmpNo);
      return WebResponse.<List<DetailSalary>>builder().status(HttpStatus.OK.value()).data(salaries).build();
   }
}
