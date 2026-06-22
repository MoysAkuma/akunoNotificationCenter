package com.akumasoft.service;

import java.beans.Transient;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.akumasoft.dto.Plantilla.CreatePlantilla.CreatePlantillaRq;
import com.akumasoft.repository.PlantillasRepository;
import com.akumasoft.mapper.PlantillaMapper;
import com.akumasoft.model.Emails.Plantillas;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlantillaServiceImp implements PlantillaService {
    private final PlantillasRepository plantillasRepository;

    @Transactional
    public UUID crearPlantilla(CreatePlantillaRq plantilla, UUID clienteId) {
        // Crear la plantilla en la base de datos
        Plantillas plantillaEntity = new PlantillaMapper().toPlantilla(plantilla, clienteId);
        
        plantillasRepository.save(plantillaEntity);
        
        log.info("Plantilla creada con ID: {}", plantillaEntity.getId());
        
        return plantillaEntity.getId();
    }
}
