package com.akumasoft.service;

import java.util.UUID;

import com.akumasoft.dto.Notificaciones.SolicitudDTO;
import com.akumasoft.dto.Notificaciones.RegisterNotification.RegisterNotificationRq;

public interface NotificationService {
    public long crearSolicitud(RegisterNotificationRq solicitud, UUID clienteId);
    public SolicitudDTO getSolicitudById(long id, UUID clienteId);
    public boolean updateSolicitud(long id, RegisterNotificationRq solicitud, UUID clienteId);
}
