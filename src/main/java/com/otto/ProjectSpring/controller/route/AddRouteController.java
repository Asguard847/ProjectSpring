package com.otto.ProjectSpring.controller.route;

import com.otto.ProjectSpring.entity.Route;
import com.otto.ProjectSpring.service.RouteService;
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
@RequestMapping("/admin/routes/addRoute")
public class AddRouteController {

    @Resource
    RouteService routeService;

    @GetMapping
    public String addRouteGet(Model model) {
        Route route = new Route();
        model.addAttribute("route", route);
        return "addRoute";
    }

    @PostMapping
    public String addRoutePost(@Valid @ModelAttribute("route") Route route,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "addRoute";
        }

        routeService.addRoute(route);
        return "redirect:/admin/routes";
    }
}
