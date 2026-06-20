package com.akumasoft.service;

import java.beans.Transient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import org.springframework.stereotype.Service;

import com.akumasoft.dto.Notificaciones.RegisterNotification.RegisterNotificationRq;
import com.akumasoft.model.Emails.Solicitudes;
import com.akumasoft.repository.EmailsBloqueadosRepository;
import com.akumasoft.repository.PlantillasRepository;
import com.akumasoft.repository.ProgramadosRepository;
import com.akumasoft.repository.QuequeRepository;
import com.akumasoft.repository.SolicitudesRepository;
import com.akumasoft.model.Emails.Plantillas;
import com.akumasoft.model.Emails.Programados;
import com.akumasoft.model.Emails.Queque;
import com.akumasoft.mapper.NotificationMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImp implements NotificationService {
    private final SolicitudesRepository solicitudesRepository;
    private final EmailsBloqueadosRepository emailsBloqueadosRepository;
    private final QuequeRepository quequeRepository;
    private final PlantillasRepository plantillasRepository;
    private final ProgramadosRepository programadosRepository;
    
    @Transactional
    public long crearSolicitud(RegisterNotificationRq solicitud) {
        // Verificar si el correo de destino está bloqueado
        if (emailsBloqueadosRepository.existsByEmail(solicitud.correo_destino())) {
            log.info("El email {} está bloqueado. No se puede crear la solicitud.", solicitud.correo_destino());
            return -1; // O lanzar una excepción personalizada
        }

        //Registrar la solicitud
        Solicitudes solicitudEntity = new NotificationMapper().toDto(solicitud);
        solicitudesRepository.createSolicitud(solicitudEntity);
        
        //Obtener los valores de la plantilla y reemplazar en el contenido HTML
        Plantillas plantilla = plantillasRepository.getPlantillaById(solicitud.plantillaId());

        //Obtener el contenido HTML de la plantilla y reemplazar los placeholders con los valores proporcionados en la solicitud
        String archivo = plantilla.getArchivo();
        //ACA IRA EL CONSUMO DEL BUCKET YA QUE TENGA LO NECESARIO PARA SU DESPLIEGUR
        String contentHTML = new NotificationMapper().reemplazarPlaceholders("<html></html>", solicitud.valores());      
        
        log.info("Solicitud creada con ID: {}", solicitudEntity.getId());

        //si viene programada, no se agrega a la cola, se espera a que el scheduler la procese
        if (solicitud.programado_date() == null) {
            // Agregar a la cola para procesamiento inmediato
            Queque quequeEntity = crearQuequeEntity(solicitudEntity, contentHTML);
            quequeRepository.createQueque(quequeEntity);
            log.info("Solicitud con ID: {} agregada a la cola para procesamiento inmediato.", solicitudEntity.getId());
        } else {
            Programados programadosEntity = crearProgramadosEntity(solicitudEntity, contentHTML);
            programadosRepository.createProgramados(programadosEntity);
            log.info("Solicitud con ID: {} programada para fecha: {}", solicitudEntity.getId(), solicitud.programado_date());
        }
        return solicitudEntity.getId();
    }


    private Queque crearQuequeEntity(Solicitudes solicitudEntity, String contentHTML) {
        Queque quequeEntity = new Queque();
        quequeEntity.setSolicitudId(solicitudEntity.getId());
        quequeEntity.setClienteId(solicitudEntity.getClienteId());
        quequeEntity.setPlantillaId(solicitudEntity.getPlantillaId());
        quequeEntity.setAsunto(solicitudEntity.getAsunto());
        quequeEntity.setContentHTML(contentHTML);
        quequeEntity.setCorreoDestino(solicitudEntity.getCorreoDestino());
        quequeEntity.setCorreoCc(solicitudEntity.getCorreoCc());
        quequeEntity.setCorreoBcc(solicitudEntity.getCorreoBcc());
        quequeEntity.setStatus(solicitudEntity.getEstado());
        quequeEntity.setRetryCount(0);
        quequeEntity.setProcesada(false);

        return quequeEntity;
    }
    
    private Programados crearProgramadosEntity(Solicitudes solicitudEntity, String contentHTML) {
        Programados programadosEntity = new Programados();
        programadosEntity.setSolicitudId(solicitudEntity.getId());
        programadosEntity.setClienteId(solicitudEntity.getClienteId());
        programadosEntity.setPlantillaId(solicitudEntity.getPlantillaId());
        programadosEntity.setAsunto(solicitudEntity.getAsunto());
        programadosEntity.setContentHTML(contentHTML);
        programadosEntity.setCorreoDestino(solicitudEntity.getCorreoDestino());
        programadosEntity.setCorreoCc(solicitudEntity.getCorreoCc());
        programadosEntity.setCorreoBcc(solicitudEntity.getCorreoBcc());
        programadosEntity.setStatus(solicitudEntity.getEstado());
        programadosEntity.setRetryCount(0);
        programadosEntity.setProcesada(false);
        programadosEntity.setProgramadoDate(solicitudEntity.getProgramadoDate());

        return programadosEntity;
    }
}
