package com.akumasoft.dto.Notificaciones;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public record SolicitudDTO(
    @JsonProperty("numero_solicitud")
    long numeroSolicitud,
    @JsonProperty("plantilla_id")
    UUID plantillaId,
    @JsonProperty("asunto")
    String asunto,
    @JsonProperty("correo_destino")
    String correoDestino,
    @JsonProperty("correo_cc")
    String correoCc,
    @JsonProperty("correo_bcc")
    String correoBcc,
    @JsonProperty("fecha_programada")
    LocalDateTime programadoDate,
    @JsonProperty("estado")
    String estatus
) {}
