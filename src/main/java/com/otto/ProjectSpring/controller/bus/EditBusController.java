package com.otto.ProjectSpring.controller.bus;


import com.otto.ProjectSpring.entity.Bus;
import com.otto.ProjectSpring.service.BusService;
import com.otto.ProjectSpring.service.DriverService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/admin/buses/editBus/{id}")
public class EditBusController {

    @Resource
    BusService busService;

    @Resource
    DriverService driverService;

    @GetMapping
    public String editBusGet(@PathVariable int id, Model model){
        Bus bus = busService.getBusById(id);
        model.addAttribute("bus", bus);

        Map<Integer, String> drivers = driverService.getFreeDrivers();
        model.addAttribute("drivers", drivers);
        return "editBus";
    }

    @PostMapping
    public String editBusPost(@PathVariable int id,
                              @Valid @ModelAttribute("bus") Bus bus,
                              @RequestParam(defaultValue = "false") boolean check,
                              BindingResult result){

        if(result.hasErrors()){
            return "editBus";
        }

        busService.updateBus(id, bus, check);
        return "redirect:/admin/buses";

    }
}
