package com.example.Attendance.repository;

import com.example.Attendance.model.Working;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkingRepository extends JpaRepository<Working, Integer> {
}
