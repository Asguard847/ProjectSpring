package com.otto.ProjectSpring;

import com.otto.ProjectSpring.entity.Bus;
import com.otto.ProjectSpring.entity.Driver;
import com.otto.ProjectSpring.entity.Route;

public class TestUtils {

    public static Bus getBus() {
        Driver driver = new Driver();
        driver.setFirstName("aaa");
        driver.setLastName("bbb");
        driver.setEmail("aaa@gmail.com");
        driver.setPhoneNumber("+380637891786");

        Route route = new Route();
        route.setNumber("18");
        route.setStartPoint("aaa");
        route.setEndPoint("bbb");
        route.setLength(18600);

        Bus bus = new Bus();
        bus.setModel("aaa");
        bus.setNumber("bbb");
        bus.setDriver(driver);
        bus.setRoute(route);
        return bus;
    }
}
