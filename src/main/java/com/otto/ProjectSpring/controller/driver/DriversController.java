package com.otto.ProjectSpring.controller.driver;

import com.otto.ProjectSpring.entity.Driver;
import com.otto.ProjectSpring.service.DriverService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/admin/drivers")
public class DriversController {

    @Resource
    DriverService driverService;

    @GetMapping
    public String drivers(Model model){
        List<Driver> drivers = driverService.getAllDrivers();
        model.addAttribute("drivers", drivers);
        return "drivers";
    }
}
