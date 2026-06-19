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
@Table(name = "solicitudes")
@Data
public class Solicitudes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name = "plantilla_id", nullable = false)
    private UUID plantillaId;
    
    @Column(name = "cliente_id", nullable = false)
    private long clienteId;
    @Column(name = "asunto", nullable = false)
    private String asunto;
    @Column(name = "correo_destino", columnDefinition = "TEXT", nullable = false)
    private String correoDestino;
    @Column(name = "correo_cc", columnDefinition = "TEXT")
    private String correoCc;
    @Column(name = "correo_bcc", columnDefinition = "TEXT")
    private String correoBcc;
    @Column(name = "programado_date")
    private LocalDateTime programadoDate;

    @Column(name = "estado", nullable = false)
    private String estado;
}
