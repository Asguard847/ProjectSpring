package com.otto.ProjectSpring.dao;

import com.otto.ProjectSpring.TestUtils;
import com.otto.ProjectSpring.entity.Driver;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class DriverRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DriverRepository driverRepository;

    @Test
    public void setReadyForTest() {

        Driver driver = TestUtils.getBus().getDriver();

        entityManager.persistAndFlush(driver);

        int driverId = driver.getId();
        driverRepository.setReadyFor(true, driverId);

        driver = driverRepository.findById(driverId).get();

        assertTrue(driver.isReady());
    }

}