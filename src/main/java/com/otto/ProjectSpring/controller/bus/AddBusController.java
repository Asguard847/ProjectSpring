package com.otto.ProjectSpring.controller.bus;

import com.otto.ProjectSpring.entity.Bus;
import com.otto.ProjectSpring.service.BusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin/buses/addBus")
public class AddBusController {

    @Resource
    BusService busService;

    @GetMapping
    public String addBusGet(Model model) {
        Bus bus = new Bus();
        model.addAttribute("bus", bus);
        return "addBus";
    }

    @PostMapping
    public String addBusPost(@Valid @ModelAttribute ("bus") Bus bus,
                             BindingResult result){

        if(result.hasErrors()){
            return "addBus";
        }

        busService.addBus(bus);
        return "redirect:/admin/buses";
    }
}
