package com.feyi.employeemanagementsystem.services;

import com.feyi.employeemanagementsystem.models.Leave;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LeaveService{
    void applyLeave(Leave leaveDetails);
    List<Leave> getAllLeaves();
    Leave getLeaveDetailsOnId(int id);
    void updateLeaveDetails(Leave leaveDetails);
    List<Leave> getAllActiveLeaves();
    List<Leave> getAllLeavesOfUser(String username);
    List<Leave> getAllLeavesOnStatus(boolean pending, boolean accepted, boolean rejected);

}
