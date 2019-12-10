package com.otto.ProjectSpring.dao;

import com.otto.ProjectSpring.TestUtils;
import com.otto.ProjectSpring.entity.Bus;
import com.otto.ProjectSpring.entity.Driver;
import com.otto.ProjectSpring.entity.Route;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class BusRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BusRepository busRepository;

    @Test
    public void removeDriverTest(){

        Bus bus = TestUtils.getBus();

        entityManager.persistAndFlush(bus);

        int busId = bus.getId();
        int driverId = bus.getDriver().getId();

        busRepository.removeDriver(driverId);

        bus = busRepository.findById(busId).get();

        assertTrue(bus.getDriver() == null && bus.getRoute() == null);
    }
}