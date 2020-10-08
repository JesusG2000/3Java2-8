package com.example.verigo.repos;

import com.example.verigo.bean.SickHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SickHistoryRepo extends JpaRepository<SickHistory,Long> {
}
