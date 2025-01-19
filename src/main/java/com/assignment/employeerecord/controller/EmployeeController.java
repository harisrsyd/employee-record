package com.assignment.employeerecord.controller;

import com.assignment.employeerecord.model.DetailEmployee;
import com.assignment.employeerecord.model.EmployeeAddRequest;
import com.assignment.employeerecord.model.EmployeesResponse;
import com.assignment.employeerecord.model.WebResponse;
import com.assignment.employeerecord.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "*")
public class EmployeeController {
   
   private EmployeeService employeeService;
   
   public EmployeeController(EmployeeService employeeService) {
      this.employeeService = employeeService;
   }
   
   @PostMapping("/employees")
   public WebResponse<String> addEmployee(@RequestBody EmployeeAddRequest request) {
      String response = employeeService.addEmployee(request);
      return WebResponse.<String>builder().status(HttpStatus.CREATED.value())
          .data(response).build();
   }
   
   @GetMapping("/employees")
   public WebResponse<List<EmployeesResponse>> getAllEmployees() {
      List<EmployeesResponse> employees = employeeService.getAllEmployees();
      return WebResponse.<List<EmployeesResponse>>builder().status(HttpStatus.OK.value())
          .data(employees).build();
   }
   
   @GetMapping("/employees/{empNo}")
   public WebResponse<DetailEmployee> getDetailEmployee(@PathVariable Integer empNo) {
      DetailEmployee employee = employeeService.getDetailEmployee(empNo);
      return WebResponse.<DetailEmployee>builder().status(HttpStatus.OK.value())
          .data(employee).build();
   }
   
   @PutMapping("/employees/update")
   public WebResponse<DetailEmployee> updateEmployee(@RequestBody DetailEmployee request) {
      DetailEmployee response = employeeService.updateEmployee(request);
      return WebResponse.<DetailEmployee>builder().status(HttpStatus.OK.value())
          .data(response).build();
   }
   
   @DeleteMapping("/employees/{empNo}")
   public WebResponse<String> deleteEmployee(@PathVariable Integer empNo) {
      String response = employeeService.deleteEmployee(empNo);
      return WebResponse.<String>builder().status(HttpStatus.OK.value())
          .data(response).build();
   }
}
