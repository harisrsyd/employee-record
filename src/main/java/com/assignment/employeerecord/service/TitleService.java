package com.assignment.employeerecord.service;

import com.assignment.employeerecord.entity.Employee;
import com.assignment.employeerecord.entity.Title;
import com.assignment.employeerecord.entity.compositekey.TitleId;
import com.assignment.employeerecord.model.DetailTitle;
import com.assignment.employeerecord.repository.EmployeeRepository;
import com.assignment.employeerecord.repository.TitleRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class TitleService {
   
   private TitleRepository titleRepository;
   private EmployeeRepository employeeRepository;
   private DeptManagerService deptManagerService;
   private ValidationService validationService;
   
   public TitleService(TitleRepository titleRepository, EmployeeRepository employeeRepository, DeptManagerService deptManagerService, ValidationService validationService) {
      this.titleRepository = titleRepository;
      this.employeeRepository = employeeRepository;
      this.deptManagerService = deptManagerService;
      this.validationService = validationService;
   }
   
   @Transactional
   public void addFirstTitle(Employee employee, String name) {
      Title title = new Title();
      title.setEmpNo(employee);
      title.setTitle(name);
      title.setFromDate(employee.getHireDate());
      title.setToDate(LocalDate.of(9999,1,1));
      titleRepository.save(title);
   }
   
   @Transactional
   public void updateLatestTitle(Employee employee, LocalDate date) {
      Title title = titleRepository.findByEmpNoLatest(employee, LocalDate.of(9999, 1, 1));
      title.setToDate(date);
      titleRepository.save(title);
      if (title.getTitle().equalsIgnoreCase("Manager")) {
         deptManagerService.updateLatestDeptManager(employee);
      }
   }
   
   public List<DetailTitle> getHistoryByEmpNo(Integer empNo) {
      Employee employee = employeeRepository.findById(empNo)
              .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
      List<Title> titles = titleRepository.findTitleHistoryByEmpNo(employee);
      return titles.stream().map(this::toDetailTitle).toList();
   }
   
   private DetailTitle toDetailTitle(Title title) {
      return DetailTitle.builder()
              .title(title.getTitle())
              .fromDate(title.getFromDate())
              .toDate(title.getToDate())
              .build();
   }
   
   @Transactional
   public DetailTitle addNewTitle(DetailTitle request) {
      validationService.validate(request);
      Employee employee = employeeRepository.findById(request.getEmpNo())
          .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
      updateLatestTitle(employee, request.getFromDate());
      
      Title newTitle = new Title();
      newTitle.setEmpNo(employee);
      newTitle.setTitle(request.getTitle());
      newTitle.setFromDate(request.getFromDate());
      newTitle.setToDate(request.getToDate());
      titleRepository.save(newTitle);
      
      return toDetailTitle(newTitle);
   }
   
   private TitleId getId(DetailTitle request) {
      TitleId id = new TitleId()
          .setEmpNo(request.getEmpNo())
          .setTitle(request.getTitle())
          .setFromDate(request.getFromDate());
      return id;
   }
   
   @Transactional
   public DetailTitle updateExistingTitle(DetailTitle existing, DetailTitle request) {
      validationService.validate(request);
      TitleId id = getId(existing);
      Title title = titleRepository.findById(id)
          .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Title not found"));
      
      title.setTitle(request.getTitle());
      title.setFromDate(request.getFromDate());
      title.setToDate(request.getToDate());
      titleRepository.save(title);
      
      return toDetailTitle(title);
   }
   
   @Transactional
   public String deleteTitle(DetailTitle request) {
      TitleId id = getId(request);
      Title title = titleRepository.findById(id)
          .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Title not found"));
      
      Employee employee = employeeRepository.findById(request.getEmpNo())
          .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
      Title titleBefore = titleRepository.findByEmpNoLatest(employee, request.getFromDate());
      titleBefore.setToDate(LocalDate.of(9999,1,1));
      titleRepository.save(titleBefore);
      
      titleRepository.delete(title);
      return "Title with empNo: " + request.getEmpNo() + " and Title: " + request.getTitle() + " deleted successfully";
   }
}
