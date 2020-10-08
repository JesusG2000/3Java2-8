package com.example.verigo.repos;

import com.example.verigo.bean.PatientVisit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientVisitRepo extends JpaRepository<PatientVisit,Long> {
}
