package com.otto.ProjectSpring.service.impl;


import com.otto.ProjectSpring.dao.BusRepository;
import com.otto.ProjectSpring.dao.RouteRepository;
import com.otto.ProjectSpring.entity.Bus;
import com.otto.ProjectSpring.entity.Route;
import com.otto.ProjectSpring.service.AssignmentService;
import com.otto.ProjectSpring.service.RouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.otto.ProjectSpring.Constants.BUS_SPEED;

@Service
@Transactional
public class RouteServiceImpl implements RouteService {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Resource
    RouteRepository routeRepository;

    @Resource
    BusRepository busRepository;

    @Resource
    AssignmentService assignmentService;

    @Override
    public List<Route> getAllRoutes() {
        List<Route> routes = (ArrayList)routeRepository.findAll();
        for(Route route: routes){
            if(route.getBuses().size()!=0){
                route.setTimeInterval(calculateTimeInterval(route));
            }
        }
        return routes;

    }

    @Override
    public Route getRouteById(int id) {
        Route route = routeRepository.findById(id).get();
        route.setTimeInterval(calculateTimeInterval(route));
        return route;
    }

    @Override
    public void addRoute(Route route) {
        routeRepository.save(route);
        LOG.info("Route successfilly persisted");
    }

    @Override
    public void deleteRoute(int id) {
        Route route = routeRepository.findById(id).get();
        for(Bus bus: route.getBuses()){
            assignmentService.cancelAssignment(bus.getDriver().getId());
            bus.setRoute(null);
        }
        routeRepository.delete(route);
        LOG.info("Route {} successfully deleted", id);
    }

    @Override
    public void updateRoute(int id, Route route) {
        Route oldRoute = routeRepository.findById(id).get();
        Optional<Bus> busOptional = busRepository.findById(route.getNewBusId());

        oldRoute.setNumber(route.getNumber());
        oldRoute.setStartPoint(route.getStartPoint());
        oldRoute.setEndPoint(route.getEndPoint());
        oldRoute.setLength(route.getLength());

        busOptional.ifPresent(bus -> {
            bus.setRoute(oldRoute);
            assignmentService.createNewAssignment(bus);
        });

        routeRepository.save(oldRoute);
        LOG.info("Route {} successfully updated", id);
    }

    private int calculateTimeInterval(Route route){
        int length = route.getLength();
        int timeOneSide = length/ BUS_SPEED;
        int timeBothSides = timeOneSide * 2;
        int busesCount = route.getBuses().size();
        if(busesCount == 0){
            return 0;
        }
        return timeBothSides / busesCount;
    }
}
