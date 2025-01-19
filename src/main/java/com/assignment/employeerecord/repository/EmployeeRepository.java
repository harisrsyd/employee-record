package com.assignment.employeerecord.repository;

import com.assignment.employeerecord.entity.Employee;
import com.assignment.employeerecord.model.EmployeesResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
   
   @Query(value = "SELECT e.emp_no as empNo, CONCAT(e.first_name, ' ', e.last_name) as fullName,\n" +
       "       EXTRACT(YEAR FROM AGE(current_date, birth_date)) as age, t.title as title,\n" +
       "       d.dept_name as department, e.hire_date as hireDate, s.salary as salary\n" +
       "       FROM employees e JOIN current_dept_emp c ON c.emp_no = e.emp_no JOIN (\n" +
       "           SELECT emp_no, title\n" +
       "           FROM titles\n" +
       "           WHERE (emp_no, from_date) IN (\n" +
       "               SELECT emp_no, MAX(from_date)\n" +
       "               FROM titles\n" +
       "               GROUP BY emp_no\n" +
       "           )\n" +
       "       ) t ON e.emp_no = t.emp_no\n" +
       "       JOIN departments d ON c.dept_no = d.dept_no JOIN (\n" +
       "           SELECT emp_no, salary\n" +
       "           FROM salaries\n" +
       "           WHERE (emp_no, from_date) IN (\n" +
       "               SELECT emp_no, MAX(from_date)\n" +
       "               FROM salaries\n" +
       "               GROUP BY emp_no\n" +
       "           )\n" +
       "       ) s ON e.emp_no = s.emp_no\n" +
       "       WHERE e.is_deleted = FALSE;", nativeQuery = true)
   List<EmployeesResponse> findAllCurrentEmployees();
   
   @Query(value = "SELECT MAX(emp_no) FROM employees;", nativeQuery = true)
   Integer maxEmpNo();
}
