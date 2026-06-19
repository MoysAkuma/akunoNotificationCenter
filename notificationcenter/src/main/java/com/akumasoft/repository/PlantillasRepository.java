package com.akumasoft.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akumasoft.model.Emails.Plantillas;

public interface PlantillasRepository extends JpaRepository<Plantillas, UUID> {
    
}
