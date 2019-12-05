package com.otto.ProjectSpring.controller.driver;

import com.otto.ProjectSpring.entity.Driver;
import com.otto.ProjectSpring.service.DriverService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/admin/drivers/editDriver/{id}")
public class EditDriverController {

    @Resource
    DriverService driverService;

    @GetMapping
    public String editDriverGet(@PathVariable int id, Model model){

        Optional<Driver> driverOptional = driverService.getDriverById(id);
        model.addAttribute("driver", driverOptional.get());
        return "editDriver";
    }

    @PostMapping
    public String editDriverPost(@PathVariable int id,
                                 @Valid @ModelAttribute("driver") Driver driver,
                                 BindingResult result, HttpServletRequest request){
        if(result.hasErrors()){
            return "editDriver";
        }

        driverService.updateDriver(driver, request, id);
        return "redirect:/admin/drivers";
    }
}
