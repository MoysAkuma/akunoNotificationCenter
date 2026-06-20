package com.akumasoft.service;

import java.util.UUID;

import com.akumasoft.dto.Notificaciones.RegisterNotification.RegisterNotificationRq;

public interface NotificationService {
    public long crearSolicitud(RegisterNotificationRq solicitud, UUID clienteId);
    
}
