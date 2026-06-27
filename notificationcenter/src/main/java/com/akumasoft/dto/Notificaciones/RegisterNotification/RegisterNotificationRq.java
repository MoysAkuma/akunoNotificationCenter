package com.akumasoft.dto.Notificaciones.RegisterNotification;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;

public record RegisterNotificationRq(
    @NotNull(message = "El campo plantillaId no puede ser nulo")
    UUID plantillaId,
    @NotNull(message = "El campo correo_destino no puede ser nulo")
    String correo_destino,
    List<ValoresSolicitud> valores,
    @NotNull(message = "El campo asunto no puede ser nulo")
    String asunto,  
    @Email(message = "El campo correo_cc debe ser un correo electrónico válido")
    String correo_cc,
    @Email(message = "El campo correo_bcc debe ser un correo electrónico válido")
    String correo_bcc,
    @Future(message = "El campo programado_date debe ser una fecha futura") 
    LocalDateTime programado_date
) {}
