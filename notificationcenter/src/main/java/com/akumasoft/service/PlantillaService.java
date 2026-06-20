package com.akumasoft.service;

import java.util.UUID;

import com.akumasoft.dto.Plantilla.CreatePlantilla.CreatePlantillaRq;
import com.akumasoft.model.Emails.Plantillas;

public interface PlantillaService {
    public boolean crearPlantilla(CreatePlantillaRq solicitud, UUID clienteId);
}
