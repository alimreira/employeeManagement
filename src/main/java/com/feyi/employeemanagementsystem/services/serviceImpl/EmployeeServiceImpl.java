package com.feyi.employeemanagementsystem.services.serviceImpl;

import com.feyi.employeemanagementsystem.models.Employee;
import com.feyi.employeemanagementsystem.repositories.EmployeeRepository;
import com.feyi.employeemanagementsystem.services.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Optional<Employee> getEmployee(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee = null;
        if (optional.isPresent()) {
            employee = optional.get();
        } else {
            throw new RuntimeException("Employee id " + id + " not found");
        }
        return employee;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long employee_id, Employee employee2) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employee_id);
        Employee employee = employeeOptional.get();
        if(employee2.getFirstName() != null && !employee2.getFirstName().isBlank()) employee.setFirstName(employee2.getFirstName());
        if(employee2.getLastName() != null && !employee2.getLastName().isBlank()) employee.setLastName(employee2.getLastName());
        if(employee2.getEmail() != null && !employee2.getEmail().isBlank()) employee.setEmail(employee2.getEmail());
        if(employee2.getPassword() != null && !employee2.getPassword().isBlank()) employee.setPassword(employee2.getPassword());
        if(employee2.getRole() != null && !employee2.getRole().isBlank()) employee.setRole(employee2.getRole());
        if(employee2.getPhoneNumber() != null && !employee2.getPhoneNumber().isBlank()) employee.setPhoneNumber(employee2.getPhoneNumber());
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public void deleteEmployeeById(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection ) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return employeeRepository.findAll(pageable);
    }
}