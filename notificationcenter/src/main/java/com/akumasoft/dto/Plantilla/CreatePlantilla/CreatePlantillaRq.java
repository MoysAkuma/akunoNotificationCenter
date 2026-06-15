package com.akumasoft.dto.Plantilla.CreatePlantilla;

public record CreatePlantillaRq(
    String nombre,
    String asunto,
    String archivo,
    String descripcion,
    String codigo
) {

}
