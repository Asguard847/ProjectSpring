package com.otto.ProjectSpring.dao;

import com.otto.ProjectSpring.entity.Assignment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AssignmentRepository extends CrudRepository<Assignment, Integer> {

    List<Assignment> findByDriverId(int driverId);

    Optional<Assignment> findByDriverIdAndCancelledNull(int driverId);
}
