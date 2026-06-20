package com.akumasoft.dto.Plantilla.CreatePlantilla.responses;

import java.util.UUID;

public record CreatePlantillaRs(
    String mensaje,
    String status,
    UUID plantillaId
) {

}
