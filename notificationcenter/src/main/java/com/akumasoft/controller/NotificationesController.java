package com.akumasoft.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.akumasoft.dto.Notificaciones.SolicitudDTO;
import com.akumasoft.dto.Notificaciones.RegisterNotification.RegisterNotificationRq;
import com.akumasoft.dto.Notificaciones.RegisterNotification.responses.CreateNotificationRs;
import com.akumasoft.dto.Plantilla.PlantillaDto;
import com.akumasoft.dto.Plantilla.CreatePlantilla.CreatePlantillaRq;
import com.akumasoft.dto.Plantilla.CreatePlantilla.responses.CreatePlantillaRs;
import com.akumasoft.dto.Plantilla.EditarPlantilla.EditPlantillaRq;
import com.akumasoft.dto.Plantilla.EditarPlantilla.EditPlantillaRs;
import com.akumasoft.model.Emails.Solicitudes;
import com.akumasoft.repository.SolicitudesRepository;
import com.akumasoft.service.NotificationServiceImp;
import com.akumasoft.service.PlantillaServiceImp;
import com.akumasoft.mapper.NotificationMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/notificationes")
@RequiredArgsConstructor
public class NotificationesController {
    private final NotificationServiceImp notificationService;
    private final PlantillaServiceImp plantillaService;

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

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudDTO> verSolicitud(@PathVariable long id, 
        @RequestHeader("Cliente-Id") UUID clienteId) {
        SolicitudDTO solicitud = notificationService.getSolicitudById(id, clienteId);
        if (solicitud != null) {
            return ResponseEntity.ok(solicitud);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/plantilla")
    public ResponseEntity<CreatePlantillaRs> crearPlantilla(
        @RequestBody CreatePlantillaRq request,
        @RequestHeader("Cliente-Id") UUID clienteId,
        @RequestHeader("Api-Key") String token
    ) {
        UUID id = plantillaService.crearPlantilla(request, clienteId);
        CreatePlantillaRs response = new CreatePlantillaRs(
            "Plantilla creada exitosamente",
            "CREADA",
            id
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/plantilla/{id}")
    public PlantillaDto getPlantillaById(@PathVariable UUID id, @RequestHeader("Cliente-Id") UUID clienteId) {
        return plantillaService.getPlantillaById(id, clienteId);
    }

    @PutMapping("/plantilla/{id}")
    public ResponseEntity<EditPlantillaRs> editarPlantilla(
        @PathVariable UUID id,
        @RequestBody EditPlantillaRq request,
        @RequestHeader("Cliente-Id") UUID clienteId,
        @RequestHeader("Api-Key") String token
    ) {
        plantillaService.editarPlantilla(id, request, clienteId);
        EditPlantillaRs response = new EditPlantillaRs(
            "Plantilla editada exitosamente",
            "EDITADA"
        );
        return ResponseEntity.ok(response);
    }
    
}
