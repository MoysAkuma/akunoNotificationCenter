package com.akumasoft.dto.Notificaciones.RegisterNotification.responses;

import java.util.UUID;

public record CreateNotification200Rs(
    UUID solicitudId,
    String mensaje,
    String status
) {

}
