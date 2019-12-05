package com.otto.ProjectSpring.service;


import com.otto.ProjectSpring.entity.Assignment;
import com.otto.ProjectSpring.entity.Bus;

import java.util.List;
import java.util.Optional;

public interface AssignmentService {

    List<Assignment> getAllAssignments();
    Optional<Assignment> getActualAssignment(int id);
    void createNewAssignment(Bus bus);
    void cancelAssignment(int driverId);
    void approveAssignment(int id);
    //List<Assignment> getAllAssignmentsForDriver(int driverId);
   // void deleteAssignment(int id);
    //void setApproved(int id);
   // void setCancelled(int id);
   // void cancelForDriver(int driverId);
   // Assignment getNewForDriver(int driverId);
}
