package com.assignment.employeerecord.repository;

import com.assignment.employeerecord.entity.Title;
import com.assignment.employeerecord.entity.compositekey.TitleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRepository extends JpaRepository<Title, TitleId> {
}
