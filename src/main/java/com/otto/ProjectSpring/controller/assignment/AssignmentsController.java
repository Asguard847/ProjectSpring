package com.otto.ProjectSpring.controller.assignment;

import com.otto.ProjectSpring.entity.Assignment;
import com.otto.ProjectSpring.service.AssignmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/admin/assignments")
public class AssignmentsController {

    @Resource
    AssignmentService assignmentService;

    @GetMapping
    public String assignments(Model model){
        List<Assignment> assignments = assignmentService.getAllAssignments();
        model.addAttribute("assignments", assignments);
        return "assignments";
    }
}
