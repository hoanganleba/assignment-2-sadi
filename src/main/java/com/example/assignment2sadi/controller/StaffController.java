package com.example.assignment2sadi.controller;

import com.example.assignment2sadi.model.Staff;
import com.example.assignment2sadi.model.Staff;
import com.example.assignment2sadi.repository.StaffRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class StaffController {

    private final StaffRepository staffRepository;

    public StaffController(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @GetMapping("/staffs")
    public List<Staff> getStaffs() {
        return staffRepository.findAll();
    }

    // Get staff by staff id
    @GetMapping("/staff/{staffId}")
    public Object getStaffById(@PathVariable Integer staffId) {
        return staffRepository.findById(staffId);
    }

    // Add staff
    @PostMapping("/staff")
    public Object createStaff(@RequestBody Staff staff) {
        return staffRepository.save(staff);
    }

    // Update
    @PutMapping("/staff/{staffId}")
    public Object updateStaff(@RequestBody Staff newStaff, @PathVariable Integer staffId) {
        return staffRepository.findById(staffId).map(staff -> {
            staff.setName(newStaff.getName());
            staff.setAddress(newStaff.getAddress());
            staff.setEmail(newStaff.getEmail());
            staff.setPhoneNumber(newStaff.getPhoneNumber());
            staff.setEmail(newStaff.getEmail());
            staffRepository.save(staff);
            return ResponseEntity.ok(newStaff);
        });
    }

    // Delete
    @DeleteMapping("/staff/{staffId}")
    public void deleteStaff(@PathVariable Integer staffId) {
        staffRepository.findById(staffId).map(staff -> {
            staffRepository.delete(staff);
            return ResponseEntity.ok().build();
        });
    }
}
