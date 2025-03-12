package com.hospitalManagement.hospitalManagement.service;
import com.hospitalManagement.hospitalManagement.entity.Doctor;
import com.hospitalManagement.hospitalManagement.exception.ResourceNotFoundException;
import com.hospitalManagement.hospitalManagement.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + id));
    }

    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor updateDoctor(Long id, Doctor doctorDetails) {
        Doctor doctor = getDoctorById(id);
        doctor.setName(doctorDetails.getName());
        doctor.setAddress(doctorDetails.getAddress());
        doctor.setEmail(doctorDetails.getEmail());
        doctor.setSpecialization(doctorDetails.getSpecialization());
        doctor.setPhoneNumber(doctorDetails.getPhoneNumber());
        doctor.setHospitalName(doctorDetails.getHospitalName());
        return doctorRepository.save(doctor);
   }

    public void deleteDoctor(Long id) {
        Doctor doctor = getDoctorById(id);
        doctorRepository.delete(doctor);
    }
}
