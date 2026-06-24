package com.akumasoft.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akumasoft.model.Emails.Valores;

public interface ValoresRepository extends JpaRepository<Valores, UUID> {
    List<Valores> getValoresByPlantillaId(UUID plantillaId);
    void deleteAll(List<Valores> valores);

    void saveAll(List<Valores> valores);
}
