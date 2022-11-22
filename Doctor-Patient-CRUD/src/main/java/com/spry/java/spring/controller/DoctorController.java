package com.spry.java.spring.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spry.java.spring.entity.Doctor;
import com.spry.java.spring.entity.Patient;
import com.spry.java.spring.service.DoctorService;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@GetMapping("/list")
	public List<Doctor> listAllDoctors() {
		return doctorService.listAllDoctors();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Doctor> getDoctorById(@PathVariable("id") int doctorId) {
		return new ResponseEntity<Doctor>(doctorService.getDoctorById(doctorId), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Doctor> saveDoctor(@RequestBody Doctor doctor) {
		return new ResponseEntity<Doctor>(doctorService.saveDoctor(doctor), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDoctorById(@PathVariable("id") int doctorId) {
		doctorService.deleteDoctorById(doctorId);
		return new ResponseEntity<String>("Doctor deleted successfully.", HttpStatus.OK);
	}

	@PostMapping("/{id}/patient")
	public ResponseEntity<String> addPatientUnderDoctor(@PathVariable("id") int doctorId,
			@RequestBody Patient patient) {
		doctorService.addPatientUnderDoctor(doctorId, patient);
		return new ResponseEntity<String>("Patient added under doctor successfully.", HttpStatus.OK);
	}

	@PutMapping("/{id}/patient/{pId}")
	public ResponseEntity<String> dischargePatient(@PathVariable("id") int doctorId,
			@PathVariable("pId") int patientId) {
		doctorService.dischargePatient(doctorId, patientId);
		return new ResponseEntity<String>("Patient discharged successfully.", HttpStatus.OK);
	}
}