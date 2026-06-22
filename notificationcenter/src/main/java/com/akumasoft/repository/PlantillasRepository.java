package com.akumasoft.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.akumasoft.model.Emails.Valores;
import com.akumasoft.model.Emails.Plantillas;

public interface PlantillasRepository extends JpaRepository<Plantillas, UUID> {
    Plantillas getPlantillaById(UUID id);
    Valores getValoresByPlantillaId(UUID plantillaId);
}
