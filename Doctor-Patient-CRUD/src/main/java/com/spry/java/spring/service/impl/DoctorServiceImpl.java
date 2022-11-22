package com.spry.java.spring.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spry.java.spring.entity.Doctor;
import com.spry.java.spring.entity.Patient;
import com.spry.java.spring.exception.DoctorNotDeletedException;
import com.spry.java.spring.exception.ResourceNotFoundException;
import com.spry.java.spring.repository.DoctorRepository;
import com.spry.java.spring.repository.PatientRepository;
import com.spry.java.spring.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private PatientRepository patientRepository;

	Logger LOG = (Logger) LoggerFactory.getLogger(DoctorServiceImpl.class);

	@Override
	public Doctor saveDoctor(Doctor doctor) {
		LOG.info("Doctor registered!");
		return doctorRepository.save(doctor);
	}

	@Override
	public List<Doctor> listAllDoctors() {
		LOG.info("Find list of all doctors below,");
		return doctorRepository.findAll();
	}

	@Override
	public Doctor getDoctorById(int doctorId) {
		LOG.info("Doctor : {}", doctorId);
		
		return doctorRepository.findById(doctorId)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor", "Id", doctorId));
	}

	@Override
	public void addPatientUnderDoctor(int doctorId, Patient patient) {
		LOG.info("Adding new patient under doctor with Doctor Id : {}", doctorId);
		Doctor doctor = doctorRepository.findById(doctorId)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor", "Id", doctorId));

		doctor.setPId(patient.getPId());
		doctorRepository.save(doctor);
		patientRepository.save(patient);
	}

	@Override
	public void dischargePatient(int doctorId, int patientId) {
		LOG.info("Discharging patient with Patient Id : {} from doctor with Doctor Id : {}", patientId, doctorId);
		Doctor doctor = doctorRepository.findById(doctorId)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor", "Id", doctorId));
		Patient patient = patientRepository.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient", "Id", patientId));
		doctor.setPId(0);
		doctorRepository.save(doctor);
		patientRepository.save(patient);
	}
	
	@Override
	public void deleteDoctorById(int doctorId) {
		LOG.info("Deleting doctor with ID {}", doctorId);

		Doctor doctor = doctorRepository.findById(doctorId)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor", "Id", doctorId));

		if (doctor.getDId() == 0) {
			LOG.error("Error occured while deleting doctor ID {}", doctorId);
			throw new DoctorNotDeletedException(doctorId);

		} else {

			LOG.info("Doctor with ID : {} is deleted!", doctorId);
			doctorRepository.deleteById(doctorId);
		}
	}

}