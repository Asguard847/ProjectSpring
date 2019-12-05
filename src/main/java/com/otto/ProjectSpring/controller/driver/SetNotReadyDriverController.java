package com.otto.ProjectSpring.controller.driver;

import com.otto.ProjectSpring.service.DriverService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin/drivers/setNotReady/{id}")
public class SetNotReadyDriverController {

    @Resource
    DriverService driverService;

    @GetMapping
    public String setNotReady(@PathVariable int id){
        driverService.setNotReady(id);
        return "redirect:/admin/drivers";
    }
}
