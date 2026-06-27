package com.akumasoft.service;

import java.beans.Transient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import org.springframework.stereotype.Service;

import com.akumasoft.dto.Notificaciones.SolicitudDTO;
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
    public long crearSolicitud(
        RegisterNotificationRq solicitud, UUID clienteId) {
        // Verificar si el correo de destino está bloqueado
        if (emailsBloqueadosRepository.existsByEmail(solicitud.correo_destino())) {
            log.info("El email {} está bloqueado. No se puede crear la solicitud.", solicitud.correo_destino());
            return -1; // O lanzar una excepción personalizada
        }

        //Registrar la solicitud
        Solicitudes solicitudEntity = new NotificationMapper().toDto(solicitud);
        long solicitudId = solicitudesRepository.createSolicitud(solicitudEntity);
        
        //Obtener los valores de la plantilla y reemplazar en el contenido HTML
        Plantillas plantilla = plantillasRepository.findByIdAndClienteId(solicitud.plantillaId(), clienteId);

        //Obtener el contenido HTML de la plantilla y reemplazar los placeholders con los valores proporcionados en la solicitud
        String archivo = plantilla.getArchivo();

        //ACA IRA EL CONSUMO DEL BUCKET YA QUE TENGA LO NECESARIO PARA SU DESPLIEGUR
        String contentHTML = new NotificationMapper().reemplazarPlaceholders("<html>{{name}}</html>", solicitud.valores());      
        
        log.info("Solicitud creada con ID: {}", solicitudEntity.getId());

        //si viene programada, no se agrega a la cola, se espera a que el scheduler la procese
        if (solicitud.programado_date() == null) {
            // Agregar a la cola para procesamiento inmediato
            Queque quequeEntity = new NotificationMapper().toQuequeEntity(solicitud, solicitudId, contentHTML, clienteId);
            quequeRepository.createQueque(quequeEntity);
            log.info("Solicitud con ID: {} agregada a la cola para procesamiento inmediato.", solicitudId);
        } else {
            Programados programadosEntity = new NotificationMapper().toProgramadosEntity(solicitud, solicitudId, contentHTML, clienteId);
            programadosRepository.createProgramados(programadosEntity);
            log.info("Solicitud con ID: {} programada para fecha: {}", solicitudId, solicitud.programado_date());
        }
        return solicitudId;
    }

    @Transient
    public boolean updateSolicitud(long id, RegisterNotificationRq solicitud, UUID clienteId) {
        Solicitudes existingSolicitud = solicitudesRepository.getSolicitudById(id);
        if (existingSolicitud == null || !existingSolicitud.getClienteId().equals(clienteId)) {
            log.info("Solicitud con ID: {} no encontrada o no pertenece al cliente: {}", id, clienteId);
            return false; // Solicitud no encontrada o no pertenece al cliente
        }

        // Actualizar los campos de la solicitud existente con los nuevos valores
        existingSolicitud.setPlantillaId(solicitud.plantillaId());
        existingSolicitud.setCorreoDestino(solicitud.correo_destino());
        existingSolicitud.setAsunto(solicitud.asunto());
        existingSolicitud.setCorreoCc(solicitud.correo_cc());
        existingSolicitud.setCorreoBcc(solicitud.correo_bcc());
        existingSolicitud.setProgramadoDate(solicitud.programado_date());

        solicitudesRepository.save(existingSolicitud);
        log.info("Solicitud con ID: {} actualizada exitosamente.", id);
        return true; // Actualización exitosa
    }

    @Transient
    public SolicitudDTO getSolicitudById(long id, UUID clienteId){
        Solicitudes solicitud = solicitudesRepository.getSolicitudById(id);
        if (solicitud == null || !solicitud.getClienteId().equals(clienteId)) {
            log.info("Solicitud con ID: {} no encontrada o no pertenece al cliente: {}", id, clienteId);
            return null; // Solicitud no encontrada o no pertenece al cliente
        }
        
        String status = quequeRepository.getStatusById(solicitud.getId());

        // Convertir la entidad Solicitudes a DTO
        SolicitudDTO solicitudDTO = new SolicitudDTO(
            solicitud.getId(),
            solicitud.getPlantillaId(),
            solicitud.getAsunto(),
            solicitud.getCorreoDestino(),
            solicitud.getCorreoCc(),
            solicitud.getCorreoBcc(),
            solicitud.getProgramadoDate(),
            status
        );
        

        return solicitudDTO;
    }
    
}
