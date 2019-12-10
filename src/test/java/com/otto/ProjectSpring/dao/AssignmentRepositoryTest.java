package com.otto.ProjectSpring.dao;

import com.otto.ProjectSpring.entity.Assignment;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@DataJpaTest
class AssignmentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AssignmentRepository assignmentRepository;

    private static final String FIRST_DRIVER_NAME = "Первый Тест";
    private static final String SECOND_DRIVER_NAME = "Второй Тест";

    @Test
    public void findByDriverIdTest(){

        Assignment assignment = new Assignment();
        assignment.setDriver(FIRST_DRIVER_NAME);
        assignment.setDriverId(20);

        entityManager.persistAndFlush(assignment);

        List<Assignment> assignments = assignmentRepository.findByDriverId(20);

        assertTrue(assignments.size() == 1);
        assertEquals(FIRST_DRIVER_NAME, assignments.get(0).getDriver());
    }

    @Test
    public void findByDriverIdAndCancelledNull(){

        Assignment assignment = new Assignment();
        assignment.setDriver(FIRST_DRIVER_NAME);
        assignment.setDriverId(20);

        Assignment cancelledAssignment = new Assignment();
        cancelledAssignment.setDriverId(20);
        cancelledAssignment.setDriver(SECOND_DRIVER_NAME);
        cancelledAssignment.setCancelled(new Date());

        entityManager.persist(assignment);
        entityManager.persist(cancelledAssignment);
        entityManager.flush();

        Optional<Assignment> assignmentOptional =
                assignmentRepository.findByDriverIdAndCancelledNull(20);

        assertNotNull(assignmentOptional.get());
        assertEquals(FIRST_DRIVER_NAME, assignmentOptional.get().getDriver());

    }

}