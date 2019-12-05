package com.otto.ProjectSpring.controller.assignment;

import com.otto.ProjectSpring.service.AssignmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user/approve/{id}")
public class ApproveAssignmentController {

    @Resource
    AssignmentService assignmentService;

    @GetMapping
    public String approveAssignment(@PathVariable int id){
        assignmentService.approveAssignment(id);
        return "redirect:/user";
    }
}
