package com.otto.ProjectSpring.service;


import com.otto.ProjectSpring.entity.Route;

import java.util.List;

public interface RouteService  {

    List<Route> getAllRoutes();
    Route getRouteById(int id);
    void addRoute(Route route);
    void deleteRoute(int id);
    void updateRoute(int id, Route route);

    //boolean validateRouteInput(HttpServletRequest request);

}
