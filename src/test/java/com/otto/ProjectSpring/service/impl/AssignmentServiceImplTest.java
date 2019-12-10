package com.otto.ProjectSpring.service.impl;

import com.otto.ProjectSpring.dao.AssignmentRepository;
import com.otto.ProjectSpring.entity.Assignment;
import com.otto.ProjectSpring.service.AssignmentService;
import org.junit.Before;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class AssignmentServiceImplTest {

    @TestConfiguration
    static class AssignmentServiceImplTestContextConfiguration {

        @Bean
        public AssignmentService assignmentService() {
            return new AssignmentServiceImpl();
        }
    }

    @Autowired
    private AssignmentService assignmentService;

    @MockBean
    private AssignmentRepository assignmentRepository;

    @Test
    public void getAllAssignmentsTest(){

        List<Assignment> assignments = new ArrayList<>();
        assignments.add(new Assignment());
        assignments.add(new Assignment());

        Mockito.when(assignmentRepository.findAll()).thenReturn(assignments);

        List<Assignment> foundAssignments = assignmentService.getAllAssignments();
        assertTrue(foundAssignments.size() == 2);
    }

    @Test
    public void testPassword(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("admin"));

    }

}