package com.akumasoft.dto.Plantilla.EditarPlantilla;

import java.util.List;

import com.akumasoft.dto.Plantilla.CreatePlantilla.CreateValores;

public record EditPlantillaRq(
    String nombre,
    String asunto,
    String archivo,
    String descripcion,
    String codigo,
    List<CreateValores> valores
) { }
