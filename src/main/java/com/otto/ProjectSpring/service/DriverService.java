package com.otto.ProjectSpring.service;



import com.otto.ProjectSpring.entity.Driver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DriverService {

    List<Driver> getAllDrivers();
    Map<Integer, String> getFreeDrivers();
    Optional<Driver> getDriverById(int id);
    Optional<Driver> getDriverByEmail(String email);
    void addDriver(Driver driver, HttpServletRequest request);
    void deleteDriver(HttpServletRequest request, int id);
    void updateDriver(Driver driver, HttpServletRequest request, int id);
    void setReady(int id);
    void setNotReady(int id);


}
