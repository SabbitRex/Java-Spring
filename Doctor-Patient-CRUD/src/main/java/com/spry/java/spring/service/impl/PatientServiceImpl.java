package com.spry.java.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spry.java.spring.entity.Doctor;
import com.spry.java.spring.entity.Patient;
import com.spry.java.spring.exception.ResourceNotFoundException;
import com.spry.java.spring.repository.DoctorRepository;
import com.spry.java.spring.repository.PatientRepository;
import com.spry.java.spring.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	Logger LOG = (Logger) LoggerFactory.getLogger(PatientServiceImpl.class);

	@Override
	public Patient savePatient(Patient patient) {
		LOG.info("Saving patient record!!!");
		return patientRepository.save(patient);
	}

	@Override
	public List<Patient> listAllPatients() {
		LOG.info("Retrieving list of patient records!!!");
		return patientRepository.findAll();
	}

	@Override
	public Patient getPatientById(int patientId) {
		LOG.info("Retrieving patient record with Patient Id : {}", patientId);

		return patientRepository.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient", "Id", patientId));
	}

	@Override
	public void deletePatientById(int patientId) {

		LOG.info("Deleting patient record with Patient Id : {}", patientId);

		Patient p = patientRepository.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient", "Id", patientId));

		if (p.getDId() != 0) {

			int dId = p.getDId();
			Optional<Doctor> d = doctorRepository.findById(dId);
			doctorRepository.save(d.get());
		}

		patientRepository.deleteById(patientId);
	}

	@Override
	public Patient assignDoctorToPatient(int patientId, int doctorId) {
		LOG.info("Assigning doctor with Doctor Id : {} to patient with Patient Id : {}", doctorId, patientId);

		Patient patient = patientRepository.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient", "Id", patientId));

		Doctor doctor = doctorRepository.findById(doctorId)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor", "Id", doctorId));

		patient.setDId(doctor.getDId());
		patientRepository.save(patient);
		return patient;
	}
}