package com.feyi.employeemanagementsystem.services;

import com.feyi.employeemanagementsystem.models.Salary;
import org.springframework.stereotype.Service;

@Service
public interface SalaryService{

    String caculateSalary(Salary salary);
    Salary getSalaryStatus(Long id);
    Salary getSalary(Long id);
    Salary saveSalary(Salary salary);
}
