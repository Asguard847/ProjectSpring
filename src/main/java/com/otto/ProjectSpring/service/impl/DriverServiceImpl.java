package com.otto.ProjectSpring.service.impl;

import com.otto.ProjectSpring.ImageUtils;
import com.otto.ProjectSpring.dao.DriverRepository;
import com.otto.ProjectSpring.entity.Driver;
import com.otto.ProjectSpring.service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.invoke.MethodHandles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.otto.ProjectSpring.Constants.IMAGE_FOLDER;

@Service
public class DriverServiceImpl implements DriverService {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String F_NAME_MSG = "First name is incorrect";
    private static final String L_NAME_MSG = "Last name is incorrect";
    private static final String PHONE_MSG = "Phone number is incorrect";
    private static final String EMAIL_MSG = "Email is incorrect";

    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String PHONE = "phone";
    private static final String EMAIL = "email";
    private static final String ID = "id";

    private static final String NAME_PATTERN = "\\p{L}+";
    private static final String PHONE_PATTERN = "\\+\\d{12}";
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";

    @Resource
    DriverRepository driverRepository;

    @Override
    public List<Driver> getAllDrivers() {
        return (ArrayList) driverRepository.findAll();
    }

    @Override
    public List<Driver> getFreeDrivers() {
        List<Driver> allDrivers = (ArrayList) driverRepository.findAll();
        List<Driver> freeDrivers = allDrivers.stream()
                .filter(e -> e.isFree() && e.isReady())
                .collect(Collectors.toList());
        return freeDrivers;
    }

    @Override
    public Optional<Driver> getDriverById(int id) {
        return driverRepository.findById(id);
    }

    @Override
    public void addDriver(Driver driver, HttpServletRequest request) {
        driver.setReady(true);
        driver.setFree(true);
        driverRepository.save(driver);
        ImageUtils.saveImage(driver.getDriverImage(), request, IMAGE_FOLDER, driver.getId());
        LOG.info("Driver {} persisted successfully", driver.getId());
    }

   /* @Override
    public Driver getDriverByEmail(String email) {

        return driverDao.getDriverByEmail(email);
    }

    @Override
    public void addDriver(HttpServletRequest request) {

        Driver driver = getDriverFromRequest(request);
        int driverId = driverDao.addDriver(driver);
        ImageUtils.saveImage(request, driverId);
        LOG.info("Driver persisted: " + driverId);
    }

    @Override
    public void deleteDriver(HttpServletRequest request) {

        int id = (Integer) request.getAttribute(ID);
        driverDao.deleteDriver(id);
        ImageUtils.deleteImage(request, id);
        LOG.info("Driver deleted: " + id);
    }

    @Override
    public void updateDriver(HttpServletRequest request) {

        Driver driver = getDriverFromRequest(request);
        int id = (Integer) request.getAttribute(ID);
        driver.setId(id);
        driverDao.updateDriver(driver);
    }

    @Override
    public void setReady(int id, boolean ready) {
        driverDao.setReady(id, ready);
    }

    @Override
    public void setFree(int id, boolean free) {
        driverDao.setFree(id, free);
    }

    @Override
    public boolean validateDriverInput(HttpServletRequest request) {

        Driver driver = getDriverFromRequest(request);

        boolean driverInputIncorrect = false;

        if (validateFirstName(driver.getFirstName())) {
            request.setAttribute("fNameVal", F_NAME_MSG);
            driverInputIncorrect = true;
        }

        if (validateLastName(driver.getLastName())) {
            request.setAttribute("lNameVal", L_NAME_MSG);
            driverInputIncorrect = true;
        }

        if (validatePhone(driver.getPhoneNumber())) {
            request.setAttribute("phoneVal", PHONE_MSG);
            driverInputIncorrect = true;
        }

        if (validateEmail(driver.getEmail())) {
            request.setAttribute("emailVal", EMAIL_MSG);
            driverInputIncorrect = true;
        }

        Object id = request.getAttribute(ID);
        if (id != null) {
            driver.setId((Integer) id);
        }

        request.setAttribute("driver", driver);

        return driverInputIncorrect;
    }

    private boolean validateFirstName(String firstName) {
        if (nullOrEmpty(firstName)) {
            return true;
        }
        return !matchesPattern(firstName, NAME_PATTERN);
    }

    private boolean validateLastName(String lastName) {
        if (nullOrEmpty(lastName)) {
            return true;
        }
        return !matchesPattern(lastName, NAME_PATTERN);
    }

    private boolean validatePhone(String phone) {
        if (nullOrEmpty(phone)) {
            return true;
        }
        return !matchesPattern(phone, PHONE_PATTERN);
    }

    private boolean validateEmail(String email) {
        if (nullOrEmpty(email)) {
            return true;
        }
        return !matchesPattern(email, EMAIL_PATTERN);
    }

    private Driver getDriverFromRequest(HttpServletRequest request) {

        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        String phone = request.getParameter(PHONE);
        String email = request.getParameter(EMAIL);

        Driver driver = new Driver();
        driver.setFirstName(firstName);
        driver.setLastName(lastName);
        driver.setPhoneNumber(phone);
        driver.setEmail(email);

        return driver;
    }

    private boolean nullOrEmpty(String val) {
        return val == null || val.isEmpty();
    }

    private boolean matchesPattern(String val, String patternString) {
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(val);
        return matcher.matches();
    }*/
}

