package com.feyi.employeemanagementsystem.repositories;

import com.feyi.employeemanagementsystem.models.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Iterable<Attendance> findAllByEmployeeId (Long id);
}