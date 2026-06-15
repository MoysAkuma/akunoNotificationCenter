package com.akumasoft.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.akumasoft.dto.Notificaciones.RegisterNotification.RegisterNotificationRq;
import com.akumasoft.repository.SolicitudesRepository;

@RestController
@RequestMapping("/api/notificationes")
@RequiredArgsConstructor
public class NotificationesController {
    private final SolicitudesRepository solicitudesRepository;

    @PostMapping
    public void crearSolicitud(@RequestBody RegisterNotificationRq request) {
        
        
    }
}
