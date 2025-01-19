package com.assignment.employeerecord.service;

import com.assignment.employeerecord.entity.Employee;
import com.assignment.employeerecord.entity.Title;
import com.assignment.employeerecord.repository.TitleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TitleService {
   
   private TitleRepository titleRepository;
   
   public TitleService(TitleRepository titleRepository) {
      this.titleRepository = titleRepository;
   }
   
   @Transactional
   public void addFirstTitle(Employee employee, String name) {
      Title title = new Title();
      title.setEmpNo(employee);
      title.setTitle(name);
      title.setFromDate(employee.getHireDate());
      title.setToDate(LocalDate.of(9999,1,1));
   }
   
   @Transactional
   public void updateLatestTitle(Employee employee) {
      Title title = titleRepository.findByEmpNoAndToDateMatches(employee, LocalDate.of(9999, 1, 1));
      title.setToDate(LocalDate.now());
      titleRepository.save(title);
   }
}
