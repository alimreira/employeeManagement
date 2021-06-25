package com.feyi.employeemanagementsystem.services.serviceImpl;

import com.feyi.employeemanagementsystem.models.Attendance;
import com.feyi.employeemanagementsystem.repositories.AttendanceRepository;
import com.feyi.employeemanagementsystem.services.AttendanceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService{

 private final AttendanceRepository attendanceRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }


    @Override
    public Attendance takeAttendance(Attendance attendance) throws NullPointerException {
        return attendanceRepository.save(attendance);
    }

    @Override
    public List<Attendance> getAttendanceByEmployeeId(Long id) {
        List<Attendance> employeeAttendances = new ArrayList<>();
        attendanceRepository.findAllByEmployeeId(id).forEach(employeeAttendances::add);

        //fdhjkalfusghfwadkljhksjdhfa


        return employeeAttendances;
    }
}