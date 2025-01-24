package com.assignment.employeerecord.repository;

import com.assignment.employeerecord.entity.Employee;
import com.assignment.employeerecord.entity.Title;
import com.assignment.employeerecord.entity.compositekey.TitleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TitleRepository extends JpaRepository<Title, TitleId> {
   
   @Query(value = "SELECT * FROM titles WHERE emp_no = ?1 AND to_date = ?2", nativeQuery = true)
   Title findByEmpNoLatest(Employee emp, LocalDate toDate);
   
   List<Title> findTitleHistoryByEmpNo(Employee empNo);
}
