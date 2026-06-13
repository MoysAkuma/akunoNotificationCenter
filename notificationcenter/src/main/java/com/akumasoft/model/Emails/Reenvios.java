package com.akumasoft.model.Emails;
import java.time.LocalDateTime;
import java.util.UUID;

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
    private String asunto;
    private String correoDestino;
    private LocalDateTime fechaReenvio;
    private boolean procesada;
}
