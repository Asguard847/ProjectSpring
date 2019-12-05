package com.otto.ProjectSpring.service.impl;

import com.otto.ProjectSpring.ImageUtils;
import com.otto.ProjectSpring.dao.BusRepository;
import com.otto.ProjectSpring.dao.DriverRepository;
import com.otto.ProjectSpring.dao.UserRepository;
import com.otto.ProjectSpring.entity.Driver;
import com.otto.ProjectSpring.entity.User;
import com.otto.ProjectSpring.service.AssignmentService;
import com.otto.ProjectSpring.service.DriverService;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Resource
    DriverRepository driverRepository;

    @Resource
    BusRepository busRepository;

    @Resource
    AssignmentService assignmentService;

    @Resource
    UserRepository userRepository;

    @Resource
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<Driver> getAllDrivers() {
        return (ArrayList) driverRepository.findAll();
    }

    @Override
    public Map<Integer, String> getFreeDrivers() {
        List<Driver> allDrivers = (ArrayList) driverRepository.findAll();
        Map<Integer, String> freeDrivers = allDrivers.stream()
                .filter(e -> e.isFree() && e.isReady())
                .collect(Collectors.toMap(Driver::getId,
                        d-> d.getFirstName() + " " + d.getLastName()));
        LOG.info("Got {} drivers ready for route", freeDrivers.size());
        return freeDrivers;
    }

    @Override
    public Optional<Driver> getDriverById(int id) {
        return driverRepository.findById(id);
    }

    @Override
    public Optional<Driver> getDriverByEmail(String email) {
        return driverRepository.findByEmail(email);
    }

    @Override
    public void addDriver(Driver driver, HttpServletRequest request) {
        driver.setReady(true);
        driver.setFree(true);
        driverRepository.save(driver);
        ImageUtils.saveImage(driver.getDriverImage(), request, driver.getId());
        addUser(driver.getEmail());
        LOG.info("Driver {} persisted successfully", driver.getId());
    }

    @Override
    public void deleteDriver(HttpServletRequest request, int id) {
        Driver driver = driverRepository.findById(id).get();
        deleteUser(driver.getEmail());
        busRepository.removeDriver(id);
        assignmentService.cancelAssignment(id);
        driverRepository.deleteById(id);
        ImageUtils.deleteImage(request, id);
        LOG.info("Successfully removed driver {}", id);
    }

    @Override
    public void updateDriver(Driver driver, HttpServletRequest request, int id) {
        Driver oldDriver = driverRepository.findById(id).get();
        oldDriver.setFirstName(driver.getFirstName());
        oldDriver.setLastName(driver.getLastName());
        oldDriver.setPhoneNumber(driver.getPhoneNumber());
        oldDriver.setEmail(driver.getEmail());
        driverRepository.save(oldDriver);
        if(driver.getDriverImage()!=null){
            ImageUtils.saveImage(driver.getDriverImage(), request, id);
        }
        LOG.info("Driver {} successfully updated", id);
    }

    @Override
    @Transactional
    public void setReady(int id) {
        driverRepository.setReadyFor(true, id);
        LOG.info("Driver {} is now ready", id);
    }

    @Override
    @Transactional
    public void setNotReady(int id) {
        driverRepository.setReadyFor(false, id);
        driverRepository.setFreeFor(true, id);
        assignmentService.cancelAssignment(id);
        busRepository.removeDriver(id);
        LOG.info("Driver {} is now not ready", id);
    }

    private String generatePassword() {

        PasswordGenerator gen = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(3);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(3);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(3);

        String password = gen.generatePassword(9, lowerCaseRule,
                upperCaseRule, digitRule);
        return password;
    }

    private void addUser(String email){
        User user = new User();
        user.setUsername(email);
        user.setAuthority("ROLE_USER");
        user.setEnabled(true);

        String password = generatePassword();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
        LOG.info("User {} with password {} successfully added", email, password);
    }

    private void deleteUser(String email){
        userRepository.deleteByUsername(email);
        LOG.info("User {} has been deleted", email);
    }
}

