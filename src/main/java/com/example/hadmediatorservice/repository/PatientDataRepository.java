package com.example.hadmediatorservice.repository;

import com.example.hadmediatorservice.bean.PatientData;
import org.springframework.data.jpa.repository.JpaRepository;
//import spring.restapi.model.PatientRequest;


public interface PatientDataRepository extends JpaRepository<PatientData, Long> {

}

