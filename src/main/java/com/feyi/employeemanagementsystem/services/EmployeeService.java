package com.feyi.employeemanagementsystem.services;

import com.feyi.employeemanagementsystem.models.Employee;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {
    Optional<Employee> getEmployee(Long id);
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(long id);
    void deleteEmployeeById(long id);
    Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Long employee_id, Employee employee2);
}