package com.akumasoft.mapper;

import java.util.List;

import com.akumasoft.dto.Notificaciones.RegisterNotification.RegisterNotificationRq;
import com.akumasoft.dto.Notificaciones.RegisterNotification.ValoresSolicitud;
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
    public String reemplazarPlaceholders(String contentHTML, List<ValoresSolicitud> valores) {
        // Reemplazar los placeholders en el contenido HTML con los valores proporcionados
        if (valores != null && !valores.isEmpty()) {
            for (ValoresSolicitud valor : valores) {
                String placeholder = "{{" + valor.clave() + "}}";
                String value = valor.valor();
                contentHTML = contentHTML.replace(placeholder, value);
            }
        }
        return contentHTML;
    }

}
