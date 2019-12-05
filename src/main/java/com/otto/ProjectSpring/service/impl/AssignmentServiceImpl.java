package com.otto.ProjectSpring.service.impl;

import com.otto.ProjectSpring.dao.AssignmentRepository;
import com.otto.ProjectSpring.entity.Assignment;
import com.otto.ProjectSpring.entity.Bus;
import com.otto.ProjectSpring.service.AssignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class AssignmentServiceImpl implements AssignmentService {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Resource
    AssignmentRepository assignmentRepository;

    @Override
    public List<Assignment> getAllAssignments() {

        List<Assignment> assignments = (ArrayList)assignmentRepository.findAll();
        LOG.info("All assignments fetched successfully");
        return assignments;
    }

    @Override
    public Optional<Assignment> getActualAssignment(int id) {
        return assignmentRepository.findByDriverIdAndCancelledNull(id);
    }

    public void createNewAssignment(Bus bus){

        Assignment assignment = new Assignment();

        String assignmentDriver = bus.getDriver().getFirstName() + " "
                + bus.getDriver().getLastName();
        String assignmentBus = bus.getNumber() + " " + bus.getModel();
        String assignmentRoute = bus.getRoute().getNumber();

        assignment.setCreated(new Date());
        assignment.setDriver(assignmentDriver);
        assignment.setBus(assignmentBus);
        assignment.setRoute(assignmentRoute);
        assignment.setDriverId(bus.getDriver().getId());

        assignmentRepository.save(assignment);
        LOG.info("Assignment for driver {} successfully created", bus.getDriver().getId());
    }

    @Override
    public void cancelAssignment(int driverId) {
        Optional<Assignment> actualAssignment =
                assignmentRepository.findByDriverIdAndCancelledNull(driverId);
        actualAssignment.ifPresent(assignment -> {
            assignment.setCancelled(new Date());
            assignmentRepository.save(assignment);
            LOG.info("Assignment for driver {} has been successfully cancelled", driverId);
        });

    }

    @Override
    public void approveAssignment(int id) {
        Optional<Assignment> assignmentOptional = assignmentRepository.findById(id);
        assignmentOptional.ifPresent(assignment -> {
            assignment.setApproved(new Date());
            assignmentRepository.save(assignment);
            LOG.info("Assignment {} has been successfully approved", id);
        });

    }
}
