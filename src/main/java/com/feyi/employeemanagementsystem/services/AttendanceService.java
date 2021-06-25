package com.feyi.employeemanagementsystem.services;


import com.feyi.employeemanagementsystem.models.Attendance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AttendanceService {
    Attendance takeAttendance(Attendance attendance);
    List<Attendance> getAttendanceByEmployeeId(Long id);
}
