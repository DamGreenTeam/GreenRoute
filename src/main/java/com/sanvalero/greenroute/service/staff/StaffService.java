package com.sanvalero.greenroute.service.staff;

import com.sanvalero.greenroute.domain.Road;
import com.sanvalero.greenroute.domain.Staff;
import com.sanvalero.greenroute.domain.dto.RoadDTO;
import com.sanvalero.greenroute.domain.dto.StaffDTO;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @author: Javier
 * el 06/04/2021
 */
public interface StaffService {

    Optional<Staff> findById(long id);
    Set<Staff> findAll();
    Staff addStaff(long id, StaffDTO staffDTO);
    Staff modifyStaff(long id, StaffDTO staffDTO);
    Staff modifyStaffBySalary(long id, float salary);
    void deleteStaff(long id);

}
