package com.example.verigo.repos;

import com.example.verigo.bean.DoctorDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorDocumentRepo extends JpaRepository<DoctorDocument, Long> {
}
