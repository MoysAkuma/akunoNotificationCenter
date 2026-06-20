package com.akumasoft.dto.Plantilla.CreatePlantilla;

import java.util.List;

public record CreatePlantillaRq(
    String nombre,
    String asunto,
    String archivo,
    String descripcion,
    String codigo,
    List<CreateValores> valores
) {

}
