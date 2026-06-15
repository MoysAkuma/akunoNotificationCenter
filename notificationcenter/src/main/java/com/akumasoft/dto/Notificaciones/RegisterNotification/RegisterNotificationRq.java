package com.akumasoft.dto.Notificaciones.RegisterNotification;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.akumasoft.dto.Notificaciones.ValoresSolicitud;

public record RegisterNotificationRq(
    UUID plantillaId,
    String correo_destino,
    List<ValoresSolicitud> valores,
    String asunto,
    String correo_cc,
    String correo_bcc,
    LocalDateTime programado_date
) {}
