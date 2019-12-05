package com.otto.ProjectSpring.controller;

import com.otto.ProjectSpring.entity.Assignment;
import com.otto.ProjectSpring.entity.Driver;
import com.otto.ProjectSpring.service.AssignmentService;
import com.otto.ProjectSpring.service.DriverService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    DriverService driverService;

    @Resource
    AssignmentService assignmentService;

    @GetMapping
    public String getUser(Authentication authentication, Model model){
        String email = authentication.getName();
        Driver driver = driverService.getDriverByEmail(email).get();
        Optional<Assignment> assignmentOptional = assignmentService
                .getActualAssignment(driver.getId());

        model.addAttribute("driver", driver);
        assignmentOptional.ifPresent(assignment -> model.addAttribute("assignment", assignment));
        return "user";
    }
}
