package com.example.verigo.repos;

import com.example.verigo.bean.PatientCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientCardRepo  extends JpaRepository<PatientCard, Long> {
}
