package com.elena.fitnessserver.repositories;

import com.elena.fitnessserver.models.Program;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProgramRepository extends JpaRepository<Program, Long> {
}
