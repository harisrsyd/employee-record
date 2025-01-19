package com.assignment.employeerecord.repository;

import com.assignment.employeerecord.entity.Employee;
import com.assignment.employeerecord.entity.Title;
import com.assignment.employeerecord.entity.compositekey.TitleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TitleRepository extends JpaRepository<Title, TitleId> {
   
   Title findByEmpNoAndToDateMatches(Employee emp, LocalDate toDate);
}
