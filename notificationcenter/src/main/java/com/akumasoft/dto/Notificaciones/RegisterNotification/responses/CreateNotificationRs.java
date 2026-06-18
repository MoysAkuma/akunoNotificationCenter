package com.akumasoft.dto.Notificaciones.RegisterNotification.responses;


public record CreateNotificationRs(
    long solicitudId,
    String mensaje,
    String status
) {

}
