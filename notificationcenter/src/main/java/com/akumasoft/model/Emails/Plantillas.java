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
@Table(name = "plantillas")
@Data
public class Plantillas {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "cliente_id", nullable = false)
    private UUID clienteId;
    
    private String nombre;

    @Column( nullable = false, length = 20)
    private String codigo;
    
    @Column(columnDefinition = "TEXT")
    private String archivo;

    @Column(name = "fecha_creado", nullable = false, updatable = false)
    private LocalDateTime fechaCreado;
}
