package com.akumasoft.service;

import java.beans.Transient;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.akumasoft.dto.Notificaciones.RegisterNotification.RegisterNotificationRq;
import com.akumasoft.model.Emails.Solicitudes;
import com.akumasoft.repository.EmailsBloqueadosRepository;
import com.akumasoft.repository.QuequeRepository;
import com.akumasoft.repository.SolicitudesRepository;
import com.akumasoft.model.Emails.Queque;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final SolicitudesRepository solicitudesRepository;
    private final EmailsBloqueadosRepository emailsBloqueadosRepository;
    private final QuequeRepository quequeRepository;
    
    @Transactional
    public Long crearSolicitud(RegisterNotificationRq solicitud) {
        // Verificar si el correo de destino está bloqueado
        if (emailsBloqueadosRepository.existsByEmail(solicitud.correo_destino())) {
            log.info("El email {} está bloqueado. No se puede crear la solicitud.", solicitud.correo_destino());
            return null; // O lanzar una excepción personalizada
        }
        
        //Registrar la solicitud
        Solicitudes solicitudEntity = crearSolicitudEntity(solicitud);
        solicitudesRepository.createSolicitud(solicitudEntity);
        log.info("Solicitud creada con ID: {}", solicitudEntity.getId());

        //si viene programada, no se agrega a la cola, se espera a que el scheduler la procese
        if (solicitud.programado_date() == null) {
            // Agregar a la cola para procesamiento inmediato
            Queque quequeEntity = crearQuequeEntity(solicitudEntity);
            quequeRepository.createQueque(quequeEntity);
            log.info("Solicitud con ID: {} agregada a la cola para procesamiento inmediato.", solicitudEntity.getId());
        } else {

            log.info("Solicitud con ID: {} programada para fecha: {}", solicitudEntity.getId(), solicitud.programado_date());
        }
        return solicitudEntity.getId();
    }

    private Solicitudes crearSolicitudEntity(RegisterNotificationRq solicitud) {
        Solicitudes solicitudEntity = new Solicitudes();
        solicitudEntity.setPlantillaId(solicitud.plantillaId());
        solicitudEntity.setCorreoDestino(solicitud.correo_destino());
        solicitudEntity.setAsunto(solicitud.asunto());
        solicitudEntity.setCorreoCc(solicitud.correo_cc());
        solicitudEntity.setCorreoBcc(solicitud.correo_bcc());
        solicitudEntity.setProgramadoDate(solicitud.programado_date());
        solicitudEntity.setEstado("PENDIENTE");
        return solicitudEntity;
    }

    private Queque crearQuequeEntity(Solicitudes solicitudEntity) {
        Queque quequeEntity = new Queque();
        quequeEntity.setSolicitudId(solicitudEntity.getId());
        quequeEntity.setClienteId(solicitudEntity.getClienteId());
        quequeEntity.setPlantillaId(solicitudEntity.getPlantillaId());
        quequeEntity.setAsunto(solicitudEntity.getAsunto());
        quequeEntity.setContentHTML(solicitudEntity.getContentHTML());
        quequeEntity.setCorreoDestino(solicitudEntity.getCorreoDestino());
        quequeEntity.setCorreoCc(solicitudEntity.getCorreoCc());
        quequeEntity.setCorreoBcc(solicitudEntity.getCorreoBcc());
        quequeEntity.setStatus(solicitudEntity.getEstado());
        quequeEntity.setRetryCount(0);
        quequeEntity.setProcesada(false);

        return quequeEntity;
    }
}
