package com.otto.ProjectSpring.service;



import com.otto.ProjectSpring.entity.Bus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BusService {

    List<Bus> getAllBuses(HttpServletRequest request);
    List<Bus> getReadyForRoute();
    Bus getBusById(int id);
    int addBus(HttpServletRequest request);
    void deleteBus(int id);
    void updateBus(HttpServletRequest request);
    void setReady(int id);
    void setNotReady(int id);
    boolean validateBusInput(HttpServletRequest request);
    void addBusToRoute(HttpServletRequest request);
    void removeBusFromRoute(HttpServletRequest request);

}
