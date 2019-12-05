package com.otto.ProjectSpring.controller.route;

import com.otto.ProjectSpring.entity.Bus;
import com.otto.ProjectSpring.service.BusService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin/routes/deleteBusFromRoute/{id}")
public class RemoveBusFromRouteController {

    @Resource
    BusService busService;

    @GetMapping
    public String removeBusFromRoute(@PathVariable int id){

        Bus bus = busService.getBusById(id);
        int routeId = bus.getRoute().getId();
        busService.removeBusFromRoute(id);
        return "redirect:/admin/routes/editRoute/" + routeId;
    }
}
