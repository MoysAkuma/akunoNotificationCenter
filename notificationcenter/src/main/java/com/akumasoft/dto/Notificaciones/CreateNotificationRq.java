package com.akumasoft.dto.Notificaciones;
import java.time.LocalDateTime;
public record CreateNotificationRq(
    String asunto,
    String contentHTML,
    String correo_destino,
    String correo_cc,
    String correo_bcc,
    LocalDateTime programado_date
) {

}
