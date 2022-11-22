package com.spry.java.spring.service;

import java.util.List;

import com.spry.java.spring.entity.Patient;

public interface PatientService {

	Patient savePatient(Patient patient);

	List<Patient> listAllPatients();
	
	Patient getPatientById(int patientId);

	void deletePatientById(int patientId);

	Patient assignDoctorToPatient(int patientId, int doctorId);
}