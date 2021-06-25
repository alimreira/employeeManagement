package com.feyi.employeemanagementsystem.controllers;

import com.feyi.employeemanagementsystem.models.Leave;
import com.feyi.employeemanagementsystem.models.User;
import com.feyi.employeemanagementsystem.services.LeaveService;
import com.feyi.employeemanagementsystem.services.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveManageService;

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userInfoService;

    @RequestMapping(value = "/user/apply-leave", method = RequestMethod.GET)
    public ModelAndView applyLeave(ModelAndView mav) {

        mav.addObject("leaveDetails", new Leave());
        mav.setViewName("applyLeave");
        return mav;
    }

    @RequestMapping(value = "/user/apply-leave", method = RequestMethod.POST)
    public ModelAndView submitApplyLeave(ModelAndView mav, @Valid Leave leaveDetails,
                                         BindingResult bindingResult) {

        User userInfo = userInfoService.getUserInfo();
        if (bindingResult.hasErrors()) {
            mav.setViewName("applyLeave");
        } else {
            leaveDetails.setUsername(userInfo.getEmail());
            leaveDetails.setEmployeeName(userInfo.getFirstName() + " " + userInfo.getLastName());
            leaveManageService.applyLeave(leaveDetails);
            mav.addObject("successMessage", "Your Leave Request is registered!");
            mav.setView(new RedirectView("/leave-management-system/user/home"));
        }
        return mav;
    }

    @RequestMapping(value = "/user/get-all-leaves", method = RequestMethod.GET)
    public @ResponseBody
    String getAllLeaves(@RequestParam(value = "pending", defaultValue = "false") boolean pending,
                        @RequestParam(value = "accepted", defaultValue = "false") boolean accepted,
                        @RequestParam(value = "rejected", defaultValue = "false") boolean rejected) throws Exception {

        Iterator<Leave> iterator = leaveManageService.getAllLeaves().iterator();
        if (pending || accepted || rejected)
            iterator = leaveManageService.getAllLeavesOnStatus(pending, accepted, rejected).iterator();
        JSONArray jsonArr = new JSONArray();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
        Calendar calendar = Calendar.getInstance();

        while (iterator.hasNext()) {
            Leave leaveDetails = iterator.next();
            calendar.setTime(leaveDetails.getToDate());
            calendar.add(Calendar.DATE, 1);

            JSONObject jsonObj = new JSONObject();
            jsonObj.put("title", leaveDetails.getEmployeeName());
            jsonObj.put("start", dateFormat.format(leaveDetails.getFromDate()));
            jsonObj.put("end", dateFormat.format(calendar.getTime()));
            if (leaveDetails.isActive())
                jsonObj.put("color", "#0878af");
            if (!leaveDetails.isActive() && leaveDetails.isAcceptRejectFlag())
                jsonObj.put("color", "green");
            if (!leaveDetails.isActive() && !leaveDetails.isAcceptRejectFlag())
                jsonObj.put("color", "red");
            jsonArr.put(jsonObj);
        }

        return jsonArr.toString();
    }

    @RequestMapping(value="/user/manage-leaves",method= RequestMethod.GET)
    public ModelAndView manageLeaves(ModelAndView mav) {

        mav.addObject("leavesList", leaveManageService.getAllActiveLeaves());
        mav.setViewName("manageLeaves");
        return mav;
    }

    @RequestMapping(value = "/user/manage-leaves/{action}/{id}", method = RequestMethod.GET)
    public ModelAndView acceptOrRejectLeaves(ModelAndView mav, @PathVariable("action") String action,
                                             @PathVariable("id") int id) {
        Leave leaveDetails = leaveManageService.getLeaveDetailsOnId(id);
        if (action.equals("accept")) {
            leaveDetails.setAcceptRejectFlag(true);
            leaveDetails.setActive(false);
        } else if (action.equals("reject")) {
            leaveDetails.setAcceptRejectFlag(false);
            leaveDetails.setActive(false);
        }
        leaveManageService.updateLeaveDetails(leaveDetails);
        mav.addObject("successMessage", "Updated Successfully!");
        mav.setView(new RedirectView("/leave-management-system/user/manage-leaves"));
        return mav;
    }

    @RequestMapping(value = "/user/my-leaves", method = RequestMethod.GET)
    public ModelAndView showMyLeaves(ModelAndView mav) {

        User userInfo = userInfoService.getUserInfo();
        List<Leave> leavesList = leaveManageService.getAllLeavesOfUser(userInfo.getEmail());
        mav.addObject("leavesList", leavesList);
        mav.setViewName("myLeaves");
        return mav;
    }
}