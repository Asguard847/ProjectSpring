package com.otto.ProjectSpring.controller.driver;

import com.otto.ProjectSpring.entity.Driver;
import com.otto.ProjectSpring.service.DriverService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;



@Controller
@RequestMapping("/admin/drivers/addDriver")
public class AddDriverController {

    @Resource
    DriverService driverService;


    @GetMapping
    public String addDriverGet(Model model){
        Driver driver = new Driver();
        model.addAttribute("driver", driver);
        return "addDriver";
    }

    @PostMapping
    public String addDriverPost(@Valid @ModelAttribute("driver") Driver driver,
                                BindingResult result, HttpServletRequest request){

        if(result.hasErrors()){
            return "addDriver";
        }
        driverService.addDriver(driver, request);

        return "redirect:/admin/drivers";
    }
}
