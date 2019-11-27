package com.otto.ProjectSpring.dao;

import com.otto.ProjectSpring.entity.Bus;
import org.springframework.data.repository.CrudRepository;

public interface BusRepository extends CrudRepository<Bus, Integer> {
}
