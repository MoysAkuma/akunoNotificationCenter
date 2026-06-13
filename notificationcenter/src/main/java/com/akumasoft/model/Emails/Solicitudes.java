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
@Table(name = "solicitudes")
@Data
public class Solicitudes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private UUID template_id;
    private long cliente_id;
    private String asunto;
    private String contentHTML;
    private String correoDestino;
    private String correoCc;
    private String correoBcc;
    private LocalDateTime programadoDate;
}
