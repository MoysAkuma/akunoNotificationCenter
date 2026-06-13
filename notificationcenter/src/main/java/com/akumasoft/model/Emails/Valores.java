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
@Table(name = "solicitudes_valores")
@Data
public class Valores {
    private UUID solicitud_id;
    private UUID plantilla_id;
    private String clave;
    private String valor;
}
