package com.akumasoft.mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.akumasoft.dto.Notificaciones.RegisterNotification.RegisterNotificationRq;
import com.akumasoft.dto.Notificaciones.RegisterNotification.ValoresSolicitud;
import com.akumasoft.model.Emails.Programados;
import com.akumasoft.model.Emails.Queque;
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

    public Queque toQuequeEntity(
        RegisterNotificationRq rq,
        long solicitudId, 
        String contentHTML, 
        UUID clienteId) {
        Queque quequeEntity = new Queque();
        
        quequeEntity.setClienteId(clienteId);
        quequeEntity.setSolicitudId(solicitudId);
        quequeEntity.setContentHTML(contentHTML);
        quequeEntity.setPlantillaId(rq.plantillaId());
        quequeEntity.setAsunto(rq.asunto());
        quequeEntity.setCorreoDestino(rq.correo_destino());        
        quequeEntity.setStatus("PENDING");
        quequeEntity.setRetryCount(0);
        quequeEntity.setProcesada(false);
        quequeEntity.setFechaCreado(LocalDateTime.now());
        quequeEntity.setFechaEnvio(null);
        quequeEntity.setFechaEnviado(null);
        
        if (rq.correo_cc() != null) {
            quequeEntity.setCorreoCc(rq.correo_cc());
        }
        if (rq.correo_bcc() != null) {
            quequeEntity.setCorreoBcc(rq.correo_bcc());
        }
        return quequeEntity;
    }

    public Programados toProgramadosEntity(
        RegisterNotificationRq rq, 
        long solicitudId, 
        String contentHTML, 
        UUID clienteId) {
        Programados programadosEntity = new Programados();
        programadosEntity.setClienteId(clienteId);
        programadosEntity.setSolicitudId(solicitudId);
        programadosEntity.setContentHTML(contentHTML);

        programadosEntity.setAsunto(rq.asunto());
        programadosEntity.setCorreoDestino(rq.correo_destino());
        programadosEntity.setCorreoCc(rq.correo_cc());
        programadosEntity.setCorreoBcc(rq.correo_bcc());
        programadosEntity.setFechaProgramado(rq.programado_date());
        programadosEntity.setProcesada(false);
        programadosEntity.setFechaCreado(LocalDateTime.now());
        return programadosEntity;
    }
}
