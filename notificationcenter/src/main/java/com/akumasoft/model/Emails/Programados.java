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
@Table(name = "prog")
@Data
public class Programados {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @Column(name = "cliente_id", nullable = false)
    private UUID clienteId;
    
    @Column(name = "solicitud_id", 
    nullable = false)
    private long solicitudId;
    
    @Column(name = "asunto", nullable = false)
    private String asunto;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String contentHTML;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String correoDestino;
    
    @Column(name = "correo_cc", columnDefinition = "TEXT")
    private String correoCc;
    
    @Column(name = "correo_bcc", columnDefinition = "TEXT")
    private String correoBcc;
    
    @Column(name = "fecha_programado", nullable = false)
    private LocalDateTime  fechaProgramado;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fecha_creado", nullable = false, updatable = false)
    private LocalDateTime fechaCreado;
    
    private boolean procesada;
}
