package com.akumasoft.dto.Onboarding;

public record RegisterServiceRq(
    String nombre,
    String servicio,
    Configuracion configuracion,
    Contacto contacto
) { }
