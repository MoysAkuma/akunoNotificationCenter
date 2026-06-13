package com.akumasoft.model.Plantillas;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "plantillas_valores")
@Data
public class Valores {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    private String clave;
    private String descripcion;
    private boolean esObligatorio;
    private LocalDateTime fechaCreado;
    
}
