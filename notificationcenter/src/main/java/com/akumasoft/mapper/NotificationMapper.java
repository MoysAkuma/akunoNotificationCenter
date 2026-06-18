package com.akumasoft.mapper;

import com.akumasoft.dto.Notificaciones.RegisterNotification.RegisterNotificationRq;
import com.akumasoft.model.Emails.Solicitudes;

public class NotificationMapper {

    public Solicitudes toDto(RegisterNotificationRq request) {
        if (request == null) {
            return null;
        }

        Solicitudes requestEntity = new Solicitudes();
        requestEntity.setPlantillaId(request.plantillaId());
        requestEntity.setCorreoDestino(request.correo_destino());
        requestEntity.setAsunto(request.asunto());
        requestEntity.setCorreoCc(request.correo_cc());
        requestEntity.setCorreoBcc(request.correo_bcc());
        requestEntity.setProgramadoDate(request.programado_date());
        return requestEntity;
    }


}
