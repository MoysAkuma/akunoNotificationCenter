package com.akumasoft.model.Emails;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "queque")
@Data

public class Queque {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;
    @Column(name = "solicitud_id", nullable = false)
    private Long solicitudId;
    @Column(name = "plantilla_id", nullable = false)
    private UUID plantillaId;
    @Column(name = "asunto", nullable = false)
    private String asunto;
    @Column(name = "content_html", columnDefinition = "TEXT", nullable = false)
    private String contentHTML;
    
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "correo_destino", columnDefinition = "TEXT", nullable = false)
    private String correoDestino;
    @Column(name = "correo_cc", columnDefinition = "TEXT")
    private String correoCc;
    @Column(name = "correo_bcc", columnDefinition = "TEXT")
    private String correoBcc;

    @Column(name = "error_message", columnDefinition = "TEXT")
    private String errorMessage;
    @Column(name = "retry_count", nullable = false)
    private int retryCount;

    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;
    @Column(name = "fecha_creado", nullable = false, updatable = false)
    private LocalDateTime fechaCreado;
    @Column(name = "fecha_enviado")
    private LocalDateTime  fechaEnviado;
    @Column(name = "procesada", nullable = false)
    private boolean procesada;
}
