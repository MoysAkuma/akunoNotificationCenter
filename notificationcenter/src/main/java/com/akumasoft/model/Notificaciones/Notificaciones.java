package com.akumasoft.model;
import java.lang.annotation.Inherited;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Table(name = "notificaciones")
@Data
public class Notificaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private UUID template_id;
    private String asunto;
    private String contentHTML;
    private String correo_destino;
    private String correo_cc;
    private String correo_bcc;
    private LocalDateTime programado_date;
    private LocalDateTime enviado_date;
    private boolean enviado;
}
