package com.elena.fitnessserver.repositories;

import com.elena.fitnessserver.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<Client, Long> {
}

