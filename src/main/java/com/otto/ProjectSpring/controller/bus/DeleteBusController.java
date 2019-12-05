package com.otto.ProjectSpring.controller.bus;

import com.otto.ProjectSpring.service.BusService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin/buses/deleteBus/{id}")
public class DeleteBusController {

    @Resource
    BusService busService;

    @GetMapping
    public String deleteBus(@PathVariable int id){
        busService.deleteBus(id);
        return "redirect:/admin/buses";
    }
}
