package com.hospitalManagement.hospitalManagement.controller;

import com.hospitalManagement.hospitalManagement.entity.Doctor;
import com.hospitalManagement.hospitalManagement.entity.Patient;
import com.hospitalManagement.hospitalManagement.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public Optional<Patient> getPatientById(@PathVariable Long id) {
        return Optional.ofNullable(patientService.getPatientById(id));
    }


    @PostMapping
    public Patient addPatient(@RequestBody Patient patient) {
        return patientService.addPatient(patient);
    }

    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        return patientService.updatePatient(id, patient);
    }
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }
}
