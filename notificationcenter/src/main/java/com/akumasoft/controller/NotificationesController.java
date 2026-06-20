package com.akumasoft.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.akumasoft.dto.Notificaciones.RegisterNotification.RegisterNotificationRq;
import com.akumasoft.dto.Notificaciones.RegisterNotification.responses.CreateNotificationRs;
import com.akumasoft.dto.Plantilla.CreatePlantilla.CreatePlantillaRq;
import com.akumasoft.dto.Plantilla.CreatePlantilla.responses.CreatePlantillaRs;
import com.akumasoft.model.Emails.Solicitudes;
import com.akumasoft.repository.SolicitudesRepository;
import com.akumasoft.service.NotificationServiceImp;
import com.akumasoft.mapper.NotificationMapper;

@RestController
@RequestMapping("/api/notificationes")
@RequiredArgsConstructor
public class NotificationesController {
    private final NotificationServiceImp notificationService;

    @PostMapping
    public ResponseEntity<CreateNotificationRs> crearSolicitud(
        @RequestBody RegisterNotificationRq request,
        @RequestHeader("Cliente-Id") UUID clienteId,
        @RequestHeader("Api-Key") String token
    ) {
        long id = notificationService.crearSolicitud(request, clienteId);
        CreateNotificationRs response = new CreateNotificationRs(
            id,
            "Solicitud creada exitosamente",
            "CREADA"
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/plantilla")
    public ResponseEntity<CreatePlantillaRs> crearPlantilla(
        @RequestBody CreatePlantillaRq request,
        @RequestHeader("Cliente-Id") UUID clienteId,
        @RequestHeader("Api-Key") String token
    ) {

        CreatePlantillaRs response = new CreatePlantillaRs(
            "Plantilla creada exitosamente",
            "CREADA",
            UUID.randomUUID()
        );
        return ResponseEntity.ok(response);
    }
}
