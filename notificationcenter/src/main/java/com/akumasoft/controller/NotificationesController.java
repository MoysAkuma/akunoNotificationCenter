package com.akumasoft.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.akumasoft.dto.Notificaciones.RegisterNotification.RegisterNotificationRq;
import com.akumasoft.dto.Notificaciones.RegisterNotification.responses.CreateNotificationRs;
import com.akumasoft.model.Emails.Solicitudes;
import com.akumasoft.repository.SolicitudesRepository;
import com.akumasoft.mapper.NotificationMapper;

@RestController
@RequestMapping("/api/notificationes")
@RequiredArgsConstructor
public class NotificationesController {
    private final SolicitudesRepository solicitudesRepository;

    @PostMapping
    public ResponseEntity<CreateNotificationRs> crearSolicitud(
        @RequestBody RegisterNotificationRq request) {
        Solicitudes requestEntity = new NotificationMapper().toDto(request);
        solicitudesRepository.createSolicitud(requestEntity);
        CreateNotificationRs response = new CreateNotificationRs(
            requestEntity.getId(),
            "Solicitud creada exitosamente",
            "CREADA"
        );
        return ResponseEntity.ok(response);
    }
}
