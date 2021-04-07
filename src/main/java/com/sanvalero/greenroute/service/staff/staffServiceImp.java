package com.sanvalero.greenroute.service.staff;

import com.sanvalero.greenroute.domain.Maintenance;
import com.sanvalero.greenroute.domain.Staff;
import com.sanvalero.greenroute.domain.dto.StaffDTO;
import com.sanvalero.greenroute.exception.MaintenanceNotFoundException;
import com.sanvalero.greenroute.exception.StaffNotFoundException;
import com.sanvalero.greenroute.repository.MaintenanceRepository;
import com.sanvalero.greenroute.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 07/04/2021
 */

@Service
public class staffServiceImp implements StaffService{

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Override
    public Optional<Staff> findById(long id) {
        return staffRepository.findById(id);
    }

    @Override
    public Set<Staff> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public Staff addStaff(long id, StaffDTO staffDTO) {
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new MaintenanceNotFoundException(id));
        Staff staff = new Staff();
        setStaff(staff, staffDTO);
        staff.setMaintenance(maintenance);
        return staffRepository.save(staff);
    }

    @Override
    public Staff modifyStaff(long id, StaffDTO staffDTO) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new StaffNotFoundException(id));
        setStaff(staff, staffDTO);
        return staffRepository.save(staff);
    }

    @Override
    public Staff modifyStaffBySalary(long id, float salary) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new StaffNotFoundException(id));
        staff.setSalary(salary);
        return staffRepository.save(staff);
    }

    @Override
    public void deleteStaff(long id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new StaffNotFoundException(id));
        staffRepository.delete(staff);
    }

    public void setStaff(Staff staff, StaffDTO staffDTO){
        staff.setName(staffDTO.getName());
        staff.setSalary(staffDTO.getSalary());
        staff.setPermanent(staffDTO.isPermanent());
        staff.setEntryDate(staffDTO.getEntryDate());
    }
}
