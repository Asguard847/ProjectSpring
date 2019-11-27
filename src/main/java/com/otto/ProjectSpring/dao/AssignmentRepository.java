package com.otto.ProjectSpring.dao;

import com.otto.ProjectSpring.entity.Assignment;
import org.springframework.data.repository.CrudRepository;

public interface AssignmentRepository extends CrudRepository<Assignment, Integer> {
}
