package com.otto.ProjectSpring.dao;

import com.otto.ProjectSpring.entity.Driver;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DriverRepository extends CrudRepository<Driver, Integer> {

    @Modifying(clearAutomatically = true)
    @Query("update Driver d set d.ready = ?1 where d.id = ?2")
    void setReadyFor( boolean ready, int id);

    @Modifying(clearAutomatically = true)
    @Query("update Driver d set d.free = ?1 where d.id = ?2")
    void setFreeFor( boolean free, int id);


    Optional<Driver> findByEmail(String email);
}
