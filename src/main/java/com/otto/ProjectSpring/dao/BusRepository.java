package com.otto.ProjectSpring.dao;

import com.otto.ProjectSpring.entity.Bus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BusRepository extends CrudRepository<Bus, Integer> {

    @Modifying(clearAutomatically = true)
    @Query("update Bus b set b.driver = null, b.route = null where b.driver.id = ?1")
    void removeDriver(int id);
}
