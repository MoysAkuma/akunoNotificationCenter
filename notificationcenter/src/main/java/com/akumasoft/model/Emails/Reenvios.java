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
@Table(name = "reenvios")
@Data
public class Reenvios {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    private UUID solicitudId;
    @Column(name = "asunto", nullable = false)
    private String asunto;
    @Column(name = "correo_destino", columnDefinition = "TEXT", nullable = false)
    private String correoDestino;
    @Column(name = "fecha_reenvio")
    private LocalDateTime fechaReenvio;
    @Column(name = "procesada", nullable = false)
    private boolean procesada;
}
