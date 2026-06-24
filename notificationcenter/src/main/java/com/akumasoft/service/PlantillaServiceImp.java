package com.akumasoft.service;

import com.akumasoft.dto.Notificaciones.SolicitudDTO;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.akumasoft.dto.Plantilla.CreatePlantilla.CreatePlantillaRq;
import com.akumasoft.dto.Plantilla.EditarPlantilla.EditPlantillaRq;
import com.akumasoft.repository.PlantillasRepository;
import com.akumasoft.repository.ValoresRepository;
import com.akumasoft.mapper.PlantillaMapper;
import com.akumasoft.model.Emails.Plantillas;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlantillaServiceImp implements PlantillaService {
    private final SolicitudDTO solicitudDTO;
    private final PlantillasRepository plantillasRepository;
    private final ValoresRepository valoresRepository;

    @Transactional
    public UUID crearPlantilla(CreatePlantillaRq plantilla, UUID clienteId) {
        // Crear la plantilla en la base de datos
        Plantillas plantillaEntity = new PlantillaMapper().toPlantilla(plantilla, clienteId);
        
        plantillasRepository.save(plantillaEntity);
        
        log.info("Plantilla creada con ID: {}", plantillaEntity.getId());
        
        return plantillaEntity.getId();
    }

    @Transactional
    public boolean editarPlantilla(UUID plantillaId, EditPlantillaRq solicitud, UUID clienteId) {
        // Buscar la plantilla existente por ID y clienteId
        Plantillas plantillaExistente = plantillasRepository.findByIdAndClienteId(plantillaId, clienteId);
        if (plantillaExistente == null) {
            log.warn("No se encontró la plantilla con ID: {} para el cliente: {}", plantillaId, clienteId);
            return false; // Plantilla no encontrada
        }
        if (!plantillaExistente.getClienteId().equals(clienteId)) {
            log.warn("El cliente con ID: {} no tiene permiso para editar la plantilla con ID: {}", clienteId, plantillaId);
            return false; // El cliente no tiene permiso para editar esta plantilla
        }
        if (solicitud.nombre() != null) {
            plantillaExistente.setNombre(solicitud.nombre());
        }
        if (solicitud.archivo() != null) {
            plantillaExistente.setArchivo(solicitud.archivo());
        }

        if (solicitud.codigo() != null) {
            plantillaExistente.setCodigo(solicitud.codigo());
        }
        
        plantillasRepository.save(plantillaExistente);
        log.info("Plantilla editada con ID: {}", plantillaId);
        if (solicitud.valores() != null) {
            // Actualizar los valores asociados a la plantilla
            var valoresExistentes = valoresRepository.getValoresByPlantillaId(plantillaId);
            // Eliminar los valores existentes
            if (valoresExistentes != null) {
                valoresRepository.deleteAll(valoresExistentes);
            }
            // Agregar los nuevos valores
            var nuevosValores = new PlantillaMapper().toValoresList(solicitud.valores(), plantillaId);
            valoresRepository.saveAll(nuevosValores);
        }
        return true;
    }

    
}
    
