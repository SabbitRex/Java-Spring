package com.spry.java.spring.service;

import java.util.List;

import com.spry.java.spring.entity.Doctor;
import com.spry.java.spring.entity.Patient;

public interface DoctorService {
	
    void deleteDoctorById(int doctorId);
    
    void addPatientUnderDoctor(int doctorId, Patient patient);

	void dischargePatient(int doctorId, int patientId);
	
	Doctor saveDoctor(Doctor doctor);
	
	List<Doctor> listAllDoctors();
	
	Doctor getDoctorById(int doctorId);
}
