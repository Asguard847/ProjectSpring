package com.otto.ProjectSpring.controller.route;

import com.otto.ProjectSpring.entity.Route;
import com.otto.ProjectSpring.service.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/admin/routes")
public class RoutesController {

    @Resource
    RouteService routeService;

    @GetMapping
    public String getRoutes(Model model){
        List<Route> routes = routeService.getAllRoutes();
        model.addAttribute("routes", routes);
        return "routes";
    }
}
