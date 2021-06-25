package com.feyi.employeemanagementsystem.controllers;

import com.feyi.employeemanagementsystem.models.Attendance;
import com.feyi.employeemanagementsystem.models.Employee;
import com.feyi.employeemanagementsystem.models.User;
import com.feyi.employeemanagementsystem.services.AttendanceService;
import com.feyi.employeemanagementsystem.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.LocalTime;
//import java.time.LocalDateTime;
//import java.time.LocalTime;


@Controller
@RequestMapping("/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;
    private final EmployeeService employeeService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService,EmployeeService employeeService) {
        this.attendanceService = attendanceService;
        this.employeeService = employeeService;
    }

    @GetMapping("/load")
    public String getAttendances(Model model, HttpSession session) {
        // checks if a user is in session (logged in)
        Employee employee = (Employee) session.getAttribute("employee");
        User employee2 = (User) session.getAttribute("employee");
        if (employee == null) return "redirect:/";
        model.addAttribute("employee", employee);
        model.addAttribute("attendance", attendanceService.getAttendanceByEmployeeId(employee.getId()));
        if(employee2.getRoles().contains("admin")) {
            return "adminpage";
        }
        return "employeepage";
    }

    @GetMapping("/take")
    public String takeAttendance(HttpSession httpSession) {
        // checks if a user is in session (logged in)
        Employee employee = (Employee) httpSession.getAttribute("employee");
        if (employee == null) return "redirect:/";
        Attendance attendance = new Attendance();
        LocalDateTime begin = LocalDateTime.now().with(LocalTime.of(3, 0));
        LocalDateTime end = LocalDateTime.now().with(LocalTime.of(16, 0));
        for (Attendance employeeAttendance: attendanceService.getAttendanceByEmployeeId(employee.getId())) {
            if(attendance.getLocalDateTime().getDayOfYear() == employeeAttendance.getLocalDateTime().getDayOfYear()) return "redirect:/attendance/load";
        }
        if(attendance.getLocalDateTime().isAfter(begin) && attendance.getLocalDateTime().isBefore(end)) {
            attendance.setEmployee(employee);
            attendanceService.takeAttendance(attendance);
        }
        return "redirect:/attendance/load";
    }

    @GetMapping("/view")
    public String viewAttendances(Model model, HttpSession httpSession) {
        // checks if a user is in session (logged in)
        Employee employee = (Employee) httpSession.getAttribute("employee");
        if (employee == null) return "redirect:/";
        model.addAttribute("employees",employeeService.getAllEmployees());
        return "employeesattendance";
    }
}