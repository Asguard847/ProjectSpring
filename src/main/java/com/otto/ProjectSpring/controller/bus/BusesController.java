package com.otto.ProjectSpring.controller.bus;

import com.otto.ProjectSpring.entity.Bus;
import com.otto.ProjectSpring.service.BusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/admin/buses")
public class BusesController {

    @Resource
    BusService busService;

    @GetMapping
    public String getBuses(Model model){
        List<Bus> buses = busService.getAllBuses();
        model.addAttribute("buses", buses);
        return "buses";
    }
}
