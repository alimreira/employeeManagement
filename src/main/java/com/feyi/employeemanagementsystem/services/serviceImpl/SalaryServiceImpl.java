package com.feyi.employeemanagementsystem.services.serviceImpl;

import com.feyi.employeemanagementsystem.models.Salary;
import com.feyi.employeemanagementsystem.repositories.SalaryRepository;
import com.feyi.employeemanagementsystem.services.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryServiceImpl implements SalaryService{

    private final SalaryRepository salaryRepository;


    @Autowired
    public SalaryServiceImpl(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    @Override
    public String caculateSalary(Salary salary) {
        String check = "";
        String check2 = String.valueOf((salary.getTotalminutesperMonth() / 60) * salary.getRatePerHour());
        check += check2;

        return check;
    }

    @Override
    public Salary getSalaryStatus(Long id) {
        return salaryRepository.findById(id).get();
    }

    @Override
    public Salary getSalary(Long id) {
        return salaryRepository.getOne(id);
    }

    @Override
    public Salary saveSalary(Salary salary) {

        Salary newSalary = new Salary();

        newSalary.setEmployee(salary.getEmployee());
        newSalary.setPaid(salary.isPaid());
        newSalary.setRatePerHour(salary.getRatePerHour());
        newSalary.setTakehome(Integer.parseInt(caculateSalary(salary)));
        newSalary.setTotalHoursPerMonth(salary.getTotalminutesperMonth()/60);
        newSalary.setTotalminutesperMonth(salary.getTotalminutesperMonth());

        return salaryRepository.save(newSalary);
    }
}
