package com.akumasoft.dto.Plantilla;

import java.util.UUID;

public record PlantillaDto(
    UUID id,
    String nombre,
    String codigo,
    String archivo,
    UUID clienteId
) {}
