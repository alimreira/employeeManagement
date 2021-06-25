package com.feyi.employeemanagementsystem.services.serviceImpl;

import com.feyi.employeemanagementsystem.models.Leave;
import com.feyi.employeemanagementsystem.repositories.LeaveRepository;
import com.feyi.employeemanagementsystem.repositories.LeaveSqlRepository;
import com.feyi.employeemanagementsystem.services.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "leaveManageService")
public class LeaveServiceImpl implements LeaveService{

    @Autowired
    private LeaveRepository leaveManageRepository;

    @Autowired
    private LeaveSqlRepository leaveManageNativeRepo;

    @SuppressWarnings("deprecation")
    public void applyLeave(Leave leaveDetails) {

        int duration = leaveDetails.getToDate().getDate() - leaveDetails.getFromDate().getDate();
        leaveDetails.setDuration(duration + 1);
        leaveDetails.setActive(true);
        leaveManageRepository.save(leaveDetails);
    }

    public List<Leave> getAllLeaves() {

        return leaveManageRepository.findAll();
    }

    public Leave getLeaveDetailsOnId(int id) {

        return leaveManageRepository.getOne(id);
    }

    public void updateLeaveDetails(Leave leaveDetails) {

        leaveManageRepository.save(leaveDetails);

    }

    public List<Leave> getAllActiveLeaves() {

        return leaveManageRepository.getAllActiveLeaves();
    }

    public List<Leave> getAllLeavesOfUser(String username) {

        return leaveManageRepository.getAllLeavesOfUser(username);

    }

    public List<Leave> getAllLeavesOnStatus(boolean pending, boolean accepted, boolean rejected) {

        StringBuffer whereQuery = new StringBuffer();
        if (pending)
            whereQuery.append("active=true or ");
        if (accepted)
            whereQuery.append("(active=false and accept_reject_flag=true) or ");
        if (rejected)
            whereQuery.append("(active=false and accept_reject_flag=false) or ");

        whereQuery.append(" 1=0");

        return leaveManageNativeRepo.getAllLeavesOnStatus(whereQuery);
    }
}