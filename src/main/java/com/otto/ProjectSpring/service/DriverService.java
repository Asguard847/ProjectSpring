package com.otto.ProjectSpring.service;



import com.otto.ProjectSpring.entity.Driver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface DriverService {

    List<Driver> getAllDrivers();
    List<Driver> getFreeDrivers();
    Optional<Driver> getDriverById(int id);
    void addDriver(Driver driver, HttpServletRequest request);
    /*Driver getDriverByEmail(String email);
    void addDriver(HttpServletRequest request);
    void deleteDriver(HttpServletRequest request);
    void updateDriver(HttpServletRequest request);
    void setReady(int id, boolean ready);

    void setFree(int id, boolean ready);

    boolean validateDriverInput(HttpServletRequest request);*/

}
