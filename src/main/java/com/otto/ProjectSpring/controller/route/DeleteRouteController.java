package com.otto.ProjectSpring.controller.route;

import com.otto.ProjectSpring.service.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin/routes/deleteRoute/{id}")
public class DeleteRouteController {

    @Resource
    RouteService routeService;

    @GetMapping
    public String deleteRoute(@PathVariable int id){
        routeService.deleteRoute(id);
        return "redirect:/admin/routes";
    }
}
