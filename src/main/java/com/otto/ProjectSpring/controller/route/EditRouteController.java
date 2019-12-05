package com.otto.ProjectSpring.controller.route;

import com.otto.ProjectSpring.entity.Route;
import com.otto.ProjectSpring.service.BusService;
import com.otto.ProjectSpring.service.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/admin/routes/editRoute/{id}")
public class EditRouteController {

    @Resource
    RouteService routeService;

    @Resource
    BusService busService;

    @GetMapping
    public String editRouteGet(@PathVariable int id, Model model){

        Route route = routeService.getRouteById(id);
        Map<Integer, String> buses = busService.getReadyBuses();

        model.addAttribute("route", route);
        model.addAttribute("buses", buses);

        return "editRoute";
    }

    @PostMapping
    public String editRoutePost(@PathVariable int id,
                                @Valid @ModelAttribute("route") Route route,
                                BindingResult result){
        if(result.hasErrors()){
            return "editRoute";
        }

        routeService.updateRoute(id, route);
        return "redirect:/admin/routes/editRoute/" + id;
    }
}
