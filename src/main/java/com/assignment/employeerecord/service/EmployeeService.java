package com.assignment.employeerecord.service;

import com.assignment.employeerecord.entity.Department;
import com.assignment.employeerecord.entity.Employee;
import com.assignment.employeerecord.model.DetailEmployee;
import com.assignment.employeerecord.model.EmployeeAddRequest;
import com.assignment.employeerecord.model.EmployeesResponse;
import com.assignment.employeerecord.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmployeeService {
   
   private EmployeeRepository employeeRepository;
   private DepartmentRepository departmentRepository;
   private DeptEmpService deptEmpService;
   private DeptManagerService deptManagerService;
   private SalaryService salaryService;
   private TitleService titleService;
   
   private ValidationService validationService;
   
   public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, DeptEmpService deptEmpService, DeptManagerService deptManagerService, SalaryService salaryService, TitleService titleService, ValidationService validationService) {
      this.employeeRepository = employeeRepository;
      this.departmentRepository = departmentRepository;
      this.deptEmpService = deptEmpService;
      this.deptManagerService = deptManagerService;
      this.salaryService = salaryService;
      this.titleService = titleService;
      this.validationService = validationService;
   }
   
   @Transactional
   public String addEmployee(EmployeeAddRequest request) {
      validationService.validate(request);
      Integer empNo = employeeRepository.maxEmpNo() + 1;
      
      Employee employee = new Employee();
      employee.setEmpNo(empNo);
      employee.setBirthDate(request.getBirthDate());
      employee.setFirstName(request.getFirstName());
      employee.setLastName(request.getLastName());
      employee.setGender(request.getGender());
      employee.setHireDate(request.getHireDate());
      employeeRepository.save(employee);
      
      Department department = departmentRepository.findByDeptName(request.getDepartment());
      if (department == null) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found");
      } else {
         if (request.getTitle().equalsIgnoreCase("Manager")) {
            deptManagerService.addDeptManager(employee, department);
         }
         deptEmpService.addDeptEmp(employee, department);
      }
      
      salaryService.addFirstSalary(employee, request.getSalary());
      titleService.addFirstTitle(employee, request.getTitle());
      
      return "Employee with empNo: " + empNo + " and Name: " + request.getFirstName() +
          " " + request.getLastName() + " added successfully";
   }
   
   
   public List<EmployeesResponse> getAllEmployees() {
      return employeeRepository.findAllCurrentEmployees();
   }
   
   public DetailEmployee toDetailEmployee(Employee employee) {
      return DetailEmployee.builder()
          .empNo(employee.getEmpNo())
          .birthDate(employee.getBirthDate())
          .firstName(employee.getFirstName())
          .lastName(employee.getLastName())
          .gender(employee.getGender())
          .hireDate(employee.getHireDate()).build();
   }
   
   public DetailEmployee getDetailEmployee(Integer empNo) {
      Employee employee = employeeRepository.findById(empNo)
          .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee data not found"));
      return toDetailEmployee(employee);
   }
   
   @Transactional
   public DetailEmployee updateEmployee(DetailEmployee request) {
      validationService.validate(request);
      Employee employee = employeeRepository.findById(request.getEmpNo())
          .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee data not found"));
      
      employee.setBirthDate(request.getBirthDate());
      employee.setFirstName(request.getFirstName());
      employee.setLastName(request.getLastName());
      employee.setGender(request.getGender());
      employee.setHireDate(request.getHireDate());
      employeeRepository.save(employee);
      
      return toDetailEmployee(employee);
   }
   
   @Transactional
   public String deleteEmployee(Integer empNo) {
      Employee employee = employeeRepository.findById(empNo)
          .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee data not found"));
      
      employee.setDeleted(true);
      employeeRepository.save(employee);
      
      deptEmpService.updateLatestDeptEmp(employee);
      titleService.updateLatestTitle(employee);
      salaryService.updateLatestSalary(employee);
      
      return "Employee with record: " + empNo + ". " + employee.getFirstName() + " " + employee.getLastName() + " deleted successfully";
   }
}
