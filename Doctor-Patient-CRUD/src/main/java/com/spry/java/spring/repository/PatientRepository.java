package com.spry.java.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spry.java.spring.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
