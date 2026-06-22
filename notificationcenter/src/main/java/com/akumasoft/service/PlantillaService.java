package com.akumasoft.service;

import java.util.UUID;

import com.akumasoft.dto.Plantilla.PlantillaDto;
import com.akumasoft.dto.Plantilla.CreatePlantilla.CreatePlantillaRq;

public interface PlantillaService {
    public boolean crearPlantilla(CreatePlantillaRq solicitud, UUID clienteId);
    public PlantillaDto getPlantillaById(UUID plantillaId, UUID clienteId);
}
