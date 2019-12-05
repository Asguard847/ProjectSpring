package com.otto.ProjectSpring.service.impl;


import com.otto.ProjectSpring.dao.BusRepository;
import com.otto.ProjectSpring.dao.DriverRepository;
import com.otto.ProjectSpring.entity.Bus;
import com.otto.ProjectSpring.entity.Driver;
import com.otto.ProjectSpring.service.AssignmentService;
import com.otto.ProjectSpring.service.BusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class BusServiceImpl implements BusService {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Resource
    BusRepository busRepository;

    @Resource
    DriverRepository driverRepository;

    @Resource
    AssignmentService assignmentService;

    @Override
    public List<Bus> getAllBuses() {
        return (ArrayList) busRepository.findAll();
    }

    @Override
    public Map<Integer, String> getReadyBuses() {
        List<Bus> allBuses = (ArrayList)busRepository.findAll();
        Map<Integer, String> readyBuses = allBuses.stream()
                .filter(b-> b.getDriver()!=null && b.isReady() && b.getRoute() == null)
                .collect(Collectors.toMap(Bus::getId, bus -> bus.getModel()
                        + " " + bus.getDriver().getFirstName()
                        + " " + bus.getDriver().getLastName()));
        LOG.info("Found {} buses ready for route", readyBuses.size());
        return readyBuses;
    }

    @Override
    public void addBus(Bus bus) {
        bus.setReady(true);
        busRepository.save(bus);
        LOG.info("Bus {} successfully persisted", bus.getId());
    }

    @Override
    public void deleteBus(int id) {
        Bus bus = busRepository.findById(id).get();
        setDriverFree(bus.getDriver());
        busRepository.deleteById(id);
        LOG.info("Bus {} successfully deleted", id);
    }

    @Override
    public Bus getBusById(int id) {
        return busRepository.findById(id).get();
    }

    @Override
    public void updateBus(int id, Bus bus, boolean checked) {

        Bus oldBus = busRepository.findById(id).get();
        int driverId = bus.getDriver().getId();
        Optional<Driver> driverOptional = driverRepository.findById(driverId);
        Driver oldDriver = oldBus.getDriver();

        oldBus.setNumber(bus.getNumber());
        oldBus.setModel(bus.getModel());
        oldBus.setReady(bus.isReady());

        if (!bus.isReady()) {
            setDriverFree(oldDriver);
            oldBus.setDriver(null);
            oldBus.setRoute(null);
            busRepository.save(oldBus);
            LOG.info("Bus {} is now not ready", id);
            return;
        }

        if(checked){
            setDriverFree(oldDriver);
            oldBus.setDriver(driverOptional.orElse(null));
            driverOptional.ifPresent(d -> d.setFree(false));
        }

        busRepository.save(oldBus);
        LOG.info("Bus {} successfully updated", id);
    }

    @Override
    public void removeBusFromRoute(int id) {

        Bus bus = busRepository.findById(id).get();
        int driverId = bus.getDriver().getId();

        bus.setRoute(null);
        busRepository.save(bus);
        assignmentService.cancelAssignment(driverId);
        LOG.info("Bus {} removed from route", id);
    }

    private void setDriverFree(Driver oldDriver) {
        if (oldDriver != null) {
            oldDriver.setFree(true);
            driverRepository.save(oldDriver);
            assignmentService.cancelAssignment(oldDriver.getId());
            LOG.info("Driver {} is now free", oldDriver.getId());
        }
    }
}
