package com.otto.ProjectSpring.controller.driver;

import com.otto.ProjectSpring.service.DriverService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/drivers/deleteDriver/{id}")
public class DeleteDriverController {

    @Resource
    DriverService driverService;

    @GetMapping
    public String deleteDriver(@PathVariable int id, HttpServletRequest request){
        driverService.deleteDriver(request, id);
        return "redirect:/admin/drivers";
    }
}
