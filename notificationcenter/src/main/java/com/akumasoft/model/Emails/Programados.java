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
@Table(name = "prog")
@Data
public class Programados {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID solicitudId;
    private String asunto;
    private String contentHTML;
    private String correoDestino;
    private String correoCc;
    private String correoBcc;

    private LocalDateTime  fechaProgramado;
    private LocalDateTime fechaCreado;
    private boolean procesada;
}
