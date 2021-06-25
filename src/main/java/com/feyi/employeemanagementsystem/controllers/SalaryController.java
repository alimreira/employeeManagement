package com.feyi.employeemanagementsystem.controllers;

import com.feyi.employeemanagementsystem.models.Employee;
import com.feyi.employeemanagementsystem.models.User;
import com.feyi.employeemanagementsystem.services.SalaryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/salary")
public class SalaryController{

    private final SalaryService salaryService;
//    private final EmployeeService employeeService;

//    @Autowired
    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
//        this.employeeService = employeeService;
    }

    @GetMapping("/load")
    public String getSalary(Model model, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("employee");
        User employee2 = (User) session.getAttribute("employee");
        if (employee == null) return "redirect:/";
        model.addAttribute("employee", employee);
        model.addAttribute("salary", salaryService.getSalaryStatus(employee.getId()));
        if(employee2.getRoles().contains("admin")) {
            return "adminpage";
        }
        return "employeepage";
    }

    @GetMapping("/view")
    public String viewSalaries(Model model, HttpSession httpSession) {
        Employee employee = (Employee) httpSession.getAttribute("employee");
        if (employee == null) return "redirect:/";
        model.addAttribute("employees", salaryService.getSalary(employee.getId()));
        return "employeessalary";
    }





}
