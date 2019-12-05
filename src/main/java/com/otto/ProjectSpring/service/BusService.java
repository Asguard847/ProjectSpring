package com.otto.ProjectSpring.service;

import com.otto.ProjectSpring.entity.Bus;

import java.util.List;
import java.util.Map;

public interface BusService {

    List<Bus> getAllBuses();
    Map<Integer, String> getReadyBuses();
    void addBus(Bus bus);
    void deleteBus(int id);
    Bus getBusById(int id);
    void updateBus(int id, Bus bus, boolean checked);
    void removeBusFromRoute(int id);

   // List<Bus> getReadyForRoute();
   // Bus getBusById(int id);
   // int addBus(HttpServletRequest request);
  //  void deleteBus(int id);
   // void updateBus(HttpServletRequest request);
   // void setReady(int id);
   // void setNotReady(int id);
   // boolean validateBusInput(HttpServletRequest request);
   // void addBusToRoute(HttpServletRequest request);
    //void removeBusFromRoute(HttpServletRequest request);

}
