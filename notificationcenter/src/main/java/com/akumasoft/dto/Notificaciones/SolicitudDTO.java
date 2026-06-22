package com.akumasoft.dto.Notificaciones;

public record SolicitudDTO(
    String destinatario,
    String asunto,
    String codigo,
    String estatus
) {}
